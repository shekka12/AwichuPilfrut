package com.umss.awichu.domain.interactorsCasosDuso.authentification.loginInteractor

import android.content.Context
import android.webkit.WebView.FindListener
import android.webkit.WebView.getCurrentWebViewPackage
import android.widget.Toast
import androidx.navigation.ActivityNavigatorExtras
import com.google.firebase.auth.FirebaseAuth
import com.umss.awichu.presentation.authentification.login.exception.FirebaseLoginException

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class SignInInteractorImpl: SignInInteractor {

    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun SignInWithEmailAndPassword(email: String, password: String, context: Context): Unit = suspendCancellableCoroutine { Continuation ->
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).addOnCompleteListener(){
            if (it.isSuccessful) {
                Continuation.resume(Unit)
            }else{
                Toast.makeText(context, "El correo o la contraseña no son válidos", Toast.LENGTH_LONG).show()
                Continuation.resumeWithException(FirebaseLoginException(it.exception?.message))


            }


        }

    }
}