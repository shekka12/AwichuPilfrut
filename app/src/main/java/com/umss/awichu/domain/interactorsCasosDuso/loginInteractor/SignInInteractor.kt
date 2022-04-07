package com.umss.awichu.domain.interactorsCasosDuso.loginInteractor

interface SignInInteractor {

    interface signInCallBack{
        fun onSignInSucces()
        fun onSignInFailure(msgError:String)

    }

    fun SignInWithEmailAndPassword(email: String, password: String, listener: signInCallBack)

}