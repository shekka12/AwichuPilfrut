package com.umss.awichu.domain.interactorsCasosDuso.authentification.passwordRecoverInteractor

import android.content.Context


interface passwordRecover {

    suspend fun sendPassWordResetEmail(email:String, context: Context)
}