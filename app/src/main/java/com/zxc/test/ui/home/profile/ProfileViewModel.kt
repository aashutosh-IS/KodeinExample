package com.zxc.test.ui.home.profile

import androidx.lifecycle.ViewModel;
import com.zxc.test.data.repo.UserRepo

class ProfileViewModel (
    repo:UserRepo
): ViewModel() {

    val user=repo.getUser()
}
