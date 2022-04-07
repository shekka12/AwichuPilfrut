package com.umss.awichu.domain.interactorsCasosDuso.registerInteractor

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.userProfileChangeRequest

class registerInteractorImpl:RegisterInteractor {

    override fun signUp(fullname: String, email: String, password: String,
                        listener: RegisterInteractor.registerCallBack) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).addOnCompleteListener { itSignUp ->
            if (itSignUp.isSuccessful){
                val profileUpdates = userProfileChangeRequest {
                    displayName = fullname
                    build()
                FirebaseAuth.getInstance().currentUser?.updateProfile(userProfileChangeRequest {  })?.addOnCompleteListener {
                    if (it.isSuccessful){
                        listener.onRegisterSucces()
                    }
                }
                }
                //listener.onRegisterSucces()

            }else{
                listener.onRegisterFailure(itSignUp.exception?.message.toString())
            }
        }
    }

}