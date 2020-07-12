package com.zxc.test.data.network

import com.zxc.test.util.ApiExceptions
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response

abstract class SafeApiRequest {

    suspend fun <T : Any> apiResponse(call: suspend () -> Response<T>): T {
        val response = call.invoke()

        if (response.isSuccessful) {
            return response.body()!!
        } else {
            val error = response.errorBody()?.string()
            val message = StringBuilder()
            error?.let {
                try {
                    message.append(JSONObject(it).getString("message"))
                } catch (e: JSONException) {

                }
                message.append("\n")
            }
            message.append("Error Code : ${response.code()}")
            throw ApiExceptions(message.toString())
        }
    }
}