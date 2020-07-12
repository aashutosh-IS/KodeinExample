package com.zxc.test.data.network.responses

import com.zxc.test.data.database.entities.User

data class AuthResponse(
    val isSuccessful: Boolean?,
    var message: String?,
    var user: User?
)