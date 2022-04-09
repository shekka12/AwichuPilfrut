package com.umss.awichu.presentation.authentification.registro.registerInteractor

interface RegisterInteractor {

    interface registerCallBack{
        fun onRegisterSucces()
        fun onRegisterFailure(msgError:String)

    }
    fun signUp(fullname:String,email:String,password:String,listener: registerCallBack)
}