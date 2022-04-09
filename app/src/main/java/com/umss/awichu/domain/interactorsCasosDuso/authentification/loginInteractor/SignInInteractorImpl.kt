package com.umss.awichu.domain.interactorsCasosDuso.authentification.loginInteractor

import com.google.firebase.auth.FirebaseAuth
import com.umss.awichu.presentation.authentification.login.exception.FirebaseLoginException
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class SignInInteractorImpl: SignInInteractor {

    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun SignInWithEmailAndPassword(email: String, password: String): Unit = suspendCancellableCoroutine { Continuation ->
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).addOnCompleteListener(){
            if (it.isSuccessful) {
                Continuation.resume(Unit)
            }else{
                Continuation.resumeWithException(FirebaseLoginException(it.exception?.message))

            }


        }

    }
}