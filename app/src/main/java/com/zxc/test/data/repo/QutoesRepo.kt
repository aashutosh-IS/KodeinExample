package com.zxc.test.data.repo

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.zxc.test.data.database.AppDatabase
import com.zxc.test.data.database.entities.Quotes
import com.zxc.test.data.network.MyApi
import com.zxc.test.data.network.SafeApiRequest
import com.zxc.test.data.pref.PrefProvider
import com.zxc.test.util.Coroutines
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

@RequiresApi(Build.VERSION_CODES.O)
class QutoesRepo(
    private val api: MyApi,
    private val db: AppDatabase,
    private val pref: PrefProvider
) : SafeApiRequest() {
    private val quotes = MutableLiveData<List<Quotes>>()

    init {
        quotes.observeForever {
            saveQuotes(it)
        }
    }

    suspend fun getQuotes():LiveData<List<Quotes>>{
        return withContext(Dispatchers.IO){
            fetchQuotes()
            db.getQuotesDao().getQuotes()
        }
    }


    private suspend fun fetchQuotes() {
        val lastSavedAt=pref.getLastSavedAt()

        if (lastSavedAt==null || isFetchNeeded(LocalDateTime.parse(lastSavedAt))) {
            val response = apiResponse { api.getQuotes() }
            quotes.postValue(response.quotes)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun isFetchNeeded(savedAt: LocalDateTime): Boolean {
        return ChronoUnit.HOURS.between(savedAt,LocalDateTime.now())>6

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun saveQuotes(quotes: List<Quotes>?) {
        Coroutines.io {
            pref.saveLastSavedAt(LocalDateTime.now().toString())
            if (quotes != null) {
                db.getQuotesDao().saveAllQuotes(quotes)
            }
        }

    }
}