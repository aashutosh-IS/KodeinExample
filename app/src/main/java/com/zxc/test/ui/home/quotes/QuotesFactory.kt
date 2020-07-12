package com.zxc.test.ui.home.quotes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zxc.test.data.repo.QutoesRepo

@Suppress("UNCHECKED_CAST")
class QuotesFactory(
    private  val repo: QutoesRepo
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return QuotesViewModel(repo)as T
    }
}