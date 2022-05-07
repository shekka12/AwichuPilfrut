package com.umss.awichu.domain.interactorsCasosDuso.authentification.passwordRecoverInteractor

import android.content.Context
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.umss.awichu.presentation.authentification.passwordRecover.exception.passwordRecoverException
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class passwordRecoverImpl: passwordRecover {
    override suspend fun sendPassWordResetEmail(email: String, context: Context): Unit = suspendCancellableCoroutine { continuation ->
        FirebaseAuth.getInstance().sendPasswordResetEmail(email).addOnCompleteListener{
            if (it.isSuccessful){
                continuation.resume(Unit)
            }else{
                Toast.makeText(context, "El formato es invalido o el correo no se encuentra registrado", Toast.LENGTH_SHORT).show()
                continuation.resumeWithException(passwordRecoverException(it.exception?.message !!))
            }
        }
    }

}