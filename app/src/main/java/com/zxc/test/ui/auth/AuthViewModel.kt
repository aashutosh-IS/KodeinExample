package com.zxc.test.ui.auth

import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModel
import com.zxc.test.data.repo.UserRepo
import com.zxc.test.util.ApiExceptions
import com.zxc.test.util.Coroutines
import com.zxc.test.util.NoInternetException

class AuthViewModel(
    private val repo: UserRepo
) : ViewModel() {

    var name: String? = null
    var email: String? = null
    var passwordConfirm: String? = null
    var password: String? = null

    var authListener: AuthListener? = null


    fun getLoggedInUserr()=repo.getUser()

    fun onLoginButtonClick(view: View) {
        authListener?.onStarted()
        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            authListener?.onFailed("Invalid Email or Password")

            return
        }

        Coroutines.main {
            try {
                val response = repo.userLogin(email!!, password!!)
                response.user?.let {
                    authListener?.onSuccess(it)
                    repo.saveUser(it)
                    return@main

                }
                authListener?.onFailed(response.message!!)

            } catch (e: ApiExceptions) {
                authListener?.onFailed(e.message!!)
            }catch (e:NoInternetException){
                authListener?.onFailed(e.message!!)

            }

        }


    }
    fun onLogin(view: View){
        Intent(view.context,LoginActivity::class.java).also {
            view.context.startActivity(it)
        }
    }
    fun onSignUp(view: View){
        Intent(view.context,SignupActivity::class.java).also {
            view.context.startActivity(it)
        }
    }
    fun onSignupButtonClick(view: View) {
        authListener?.onStarted()

        if(name.isNullOrEmpty()){
            authListener?.onFailed("Name")
            return
        }
        if(email.isNullOrEmpty()){
            authListener?.onFailed("email")
            return
        }
        if(password.isNullOrEmpty()){
            authListener?.onFailed("password")
            return
        }
        if(password!=passwordConfirm){
            authListener?.onFailed("password didnt match")
            return
        }

        Coroutines.main {
            try {
                val authResponse = repo.userSignUp(name!!,email!!, password!!)
                authResponse.user?.let {
                    authListener?.onSuccess(it)
                    repo.saveUser(it)
                    return@main

                }
                authListener?.onFailed(authResponse.message!!)

            } catch (e: ApiExceptions) {
                authListener?.onFailed(e.message!!)
            }catch (e:NoInternetException){
                authListener?.onFailed(e.message!!)

            }

        }


    }

}