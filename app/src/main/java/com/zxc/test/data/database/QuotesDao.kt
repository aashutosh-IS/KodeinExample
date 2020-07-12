package com.zxc.test.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.zxc.test.data.database.entities.Quotes


@Dao
interface QuoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAllQuotes(quotes : List<Quotes>)

    @Query("SELECT * FROM Quotes")
    fun getQuotes() : LiveData<List<Quotes>>

}