package com.umss.awichu.presentation.login

interface LoginContract {

    interface loginView{
        fun showError(msgError: String)
        fun showProgressBar()
        fun hideProgreseDialog()
        fun signIn()
        fun navigateMain()
        fun navigateToRegister()
    }
    interface LoginPresenter {
        fun attachView(view: loginView)
        fun dettachView()
        fun isVIewAtached(): Boolean
        fun signInUserWithEmailandPassword(email: String, passworw: String)
        fun checkEmptyEmail(email: String): Boolean
        fun checkEmptyPassword(passworw: String): Boolean
    }
}