package com.umss.awichu.domain.interactorsCasosDuso.authentification.loginInteractor

interface SignInInteractor {


    suspend fun SignInWithEmailAndPassword(email: String, password: String)

}