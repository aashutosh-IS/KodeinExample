package com.zxc.test.data.network

import android.content.Context
import android.net.ConnectivityManager
import com.zxc.test.util.NoInternetException
import okhttp3.Interceptor
import okhttp3.Response

@Suppress("DEPRECATION")
class Interceptors(
    context: Context
) : Interceptor {


    private val applicationContext = context.applicationContext


    override fun intercept(chain: Interceptor.Chain): Response {
        if (!internetOn())
            throw NoInternetException("Internet is not on")

        return chain.proceed(
            chain.request()
        )

    }

    private fun internetOn(): Boolean {
        val connectivityManager =
            applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        connectivityManager.activeNetworkInfo.also {
            return it != null && it.isConnected

        }
    }
}