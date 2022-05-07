package com.umss.awichu.presentation.authentification.login

import android.content.Context

interface LoginContract {

    interface loginView{
        fun showError(msgError: String?)
        fun showProgressBar()
        fun hideProgreseDialog()
        fun signIn()
        fun navigateMain()
        fun navigateToRegister()
        fun navigateToPasswordReset()
    }
    interface LoginPresenter {
        fun attachView(view: loginView)
        fun dettachView()
        fun dettachJob()
        fun isVIewAtached(): Boolean
        fun signInUserWithEmailandPassword(email: String, passworw: String, context: Context)
        fun checkEmptyEmail(email: String): Boolean
        fun checkEmptyPassword(passworw: String): Boolean
        fun checkValidEmailLogin(email: String):Boolean

    }
}