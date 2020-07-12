package com.zxc.test.ui.home.quotes

import androidx.lifecycle.ViewModel;
import com.zxc.test.data.repo.QutoesRepo
import com.zxc.test.util.lazyDeferred

class QuotesViewModel(
    repo: QutoesRepo
) : ViewModel() {

    val quotes by lazyDeferred{
        repo.getQuotes()
    }

}
