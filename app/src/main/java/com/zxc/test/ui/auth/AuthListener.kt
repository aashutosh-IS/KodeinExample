package com.zxc.test.ui.auth

import com.zxc.test.data.database.entities.User

interface AuthListener {

    fun onStarted()

    fun onSuccess(user: User)

    fun onFailed(message:String)
}