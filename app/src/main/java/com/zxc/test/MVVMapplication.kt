package com.zxc.test

import android.app.Application
import com.zxc.test.data.database.AppDatabase
import com.zxc.test.data.network.Interceptors
import com.zxc.test.data.network.MyApi
import com.zxc.test.data.pref.PrefProvider
import com.zxc.test.data.repo.QutoesRepo
import com.zxc.test.data.repo.UserRepo
import com.zxc.test.ui.auth.AuthViewModelFacatory
import com.zxc.test.ui.home.profile.ProfileViewModelFactory
import com.zxc.test.ui.home.quotes.QuotesFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class MVVMapplication : Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        import(androidXModule(this@MVVMapplication))

        bind() from singleton {
            Interceptors(instance())
        }
        bind() from singleton {
            MyApi(instance())
        }
        bind() from singleton {
            AppDatabase(instance())
        }
        bind() from singleton {
            PrefProvider(instance())
        }
        bind() from singleton {
            UserRepo(instance(), instance())
        }
        bind() from singleton {
            QutoesRepo(instance(), instance(),instance())
        }
        bind() from provider {
            AuthViewModelFacatory(instance())
        }
        bind() from provider {
            ProfileViewModelFactory(instance())
        }
        bind() from provider {
            QuotesFactory(instance())
        }


    }
}