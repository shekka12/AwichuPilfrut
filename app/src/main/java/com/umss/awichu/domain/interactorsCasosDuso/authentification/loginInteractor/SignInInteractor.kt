package com.umss.awichu.domain.interactorsCasosDuso.authentification.loginInteractor

import android.content.Context

interface SignInInteractor {


    suspend fun SignInWithEmailAndPassword(email: String, password: String, context: Context)

}