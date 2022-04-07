package com.umss.awichu.domain.interactorsCasosDuso.loginInteractor

import com.google.firebase.auth.FirebaseAuth

class SignInInteractorImpl: SignInInteractor{

    override fun SignInWithEmailAndPassword(email: String, password: String,
                                            listener: SignInInteractor.signInCallBack) {

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password).addOnCompleteListener {
            if(it.isSuccessful){
                listener.onSignInSucces()
            }else{
                listener.onSignInFailure(it.exception?.message!!)
            }

        }
    }

}