package com.zxc.test.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zxc.test.data.repo.UserRepo

@Suppress("UNCHECKED_CAST")
class AuthViewModelFacatory(
    private  val repo: UserRepo
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AuthViewModel(repo)as T
    }
}