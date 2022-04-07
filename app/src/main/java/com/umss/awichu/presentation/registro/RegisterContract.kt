package com.umss.awichu.presentation.registro

interface RegisterContract {

    interface registerView{
        fun navigateToMain()
        fun signUp()
        fun Showprogress()
        fun HideProgress()
        fun ShowError(msgError:String)

    }
    interface registerPresenter {
        fun attachView(view:registerView)
        fun isViewAttachad():Boolean
        fun detachView()
        fun checkEmptyName(fullname: String):Boolean
        //fun checkEmptyFields(fullname:String,email: String):Boolean
        fun checkValidEmail(email: String):Boolean
       // fun checkEmptyPassword(password1: String,passworod2: String)
        fun checkValidPassword(password1: String, passworod2:String):Boolean
        fun checkPasswordMatch(password1: String, passworod2:String):Boolean
        fun signUp(fullname: String, email: String, password1: String)

    }
}