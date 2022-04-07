package com.umss.awichu.domain.interactorsCasosDuso.registerInteractor

interface RegisterInteractor {

    interface registerCallBack{
        fun onRegisterSucces()
        fun onRegisterFailure(msgError:String)

    }
    fun signUp(fullname:String,email:String,password:String,listener:registerCallBack)
}