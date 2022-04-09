package com.umss.awichu.domain.interactorsCasosDuso.authentification.passwordRecoverInteractor


interface passwordRecover {

    suspend fun sendPassWordResetEmail(email:String)
}