package com.zxc.test.data.repo

import com.zxc.test.data.database.AppDatabase
import com.zxc.test.data.database.entities.User
import com.zxc.test.data.network.MyApi
import com.zxc.test.data.network.SafeApiRequest
import com.zxc.test.data.network.responses.AuthResponse

class UserRepo(
    private val api: MyApi,
    private val db: AppDatabase
) : SafeApiRequest() {

    suspend fun userLogin(email: String, password: String): AuthResponse {

        return apiResponse { api.userLogin(email, password) }

    }

    suspend fun userSignUp(name: String, email: String, password: String): AuthResponse {
        return apiResponse { api.userSignUp(name, email, password) }
    }

    suspend fun saveUser(user: User) = db.getUserDao().upsert(user)
    fun getUser() = db.getUserDao().getUser()

}