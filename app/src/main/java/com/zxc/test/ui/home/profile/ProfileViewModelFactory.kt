package com.zxc.test.ui.home.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zxc.test.data.repo.UserRepo

@Suppress("UNCHECKED_CAST")
class ProfileViewModelFactory(
    private  val repo: UserRepo
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ProfileViewModel(repo)as T
    }
}