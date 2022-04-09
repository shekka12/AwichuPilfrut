package com.umss.awichu.domain.interactorsCasosDuso.authentification.passwordRecoverInteractor

import com.google.firebase.auth.FirebaseAuth
import com.umss.awichu.presentation.authentification.passwordRecover.exception.passwordRecoverException
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class passwordRecoverImpl: passwordRecover {
    override suspend fun sendPassWordResetEmail(email: String): Unit = suspendCancellableCoroutine { continuation ->
        FirebaseAuth.getInstance().sendPasswordResetEmail(email).addOnCompleteListener{
            if (it.isSuccessful){
                continuation.resume(Unit)
            }else{
                continuation.resumeWithException(passwordRecoverException(it.exception?.message !!))
            }
        }
    }

}