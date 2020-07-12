package com.zxc.test.ui.auth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.zxc.test.R
import com.zxc.test.data.database.entities.User
import com.zxc.test.databinding.ActivityLoginBinding
import com.zxc.test.ui.home.HomeActivity
import com.zxc.test.util.hide
import com.zxc.test.util.show
import com.zxc.test.util.snackbar
import kotlinx.android.synthetic.main.activity_login.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance


class LoginActivity : AppCompatActivity(), AuthListener,KodeinAware {
    override val kodein by kodein()

    private val factory: AuthViewModelFacatory by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        val netwrokConnectionIntercepter=Interceptors(this)
//        val api = MyApi(netwrokConnectionIntercepter)
//        val appDatabase=AppDatabase(this)
//        val repo= UserRepo(api,appDatabase)
//        val factory= AuthViewModelFacatory(repo)


        val binding: ActivityLoginBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_login)
        val viewModel = ViewModelProviders.of(this, factory).get(AuthViewModel::class.java)
        binding.viewmodel = viewModel

        viewModel.authListener = this


        viewModel.getLoggedInUserr().observe(this, Observer { user ->
            if (user != null) {
                Intent(this, HomeActivity::class.java).also {
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }
            }
        })


    }

    override fun onStarted() {
        progress_bar.show()
    }

    override fun onSuccess(user: User) {
        progress_bar.hide()
    }

    override fun onFailed(message: String) {
        progress_bar.hide()
        root_layout.snackbar(message)


    }

}