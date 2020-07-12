package com.zxc.test.data.pref

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager


private const val KEY_SAVED_AT="key_saved_at"
class PrefProvider (
    context: Context
){
    private val appContext=context.applicationContext

    private val prefrence: SharedPreferences
    get() = PreferenceManager.getDefaultSharedPreferences(appContext)


    fun saveLastSavedAt(savedAt:String){
        prefrence.edit().putString(KEY_SAVED_AT,savedAt).apply()
    }
    fun getLastSavedAt():String?{
        return prefrence.getString(KEY_SAVED_AT,null)
    }
}