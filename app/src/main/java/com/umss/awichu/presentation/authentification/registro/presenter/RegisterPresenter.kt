package com.umss.awichu.presentation.authentification.registro.presenter

import androidx.core.util.PatternsCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.auth.User

import com.umss.awichu.presentation.authentification.registro.registerInteractor.RegisterInteractor
import com.umss.awichu.presentation.authentification.registro.RegisterContract

import java.util.*


class RegisterPresenter(registerInteractor: RegisterInteractor): RegisterContract.registerPresenter {

    var view: RegisterContract.registerView? = null
    var registerInteractor: RegisterInteractor? = null
    var mDatabase = FirebaseDatabase.getInstance().reference
    var mAuth = FirebaseAuth.getInstance()
    init{
        this.registerInteractor = registerInteractor
    }


    override fun attachView(view: RegisterContract.registerView) {
        this.view = view
    }

    override fun isViewAttachad(): Boolean {
        return true
    }

    override fun detachView() {
        view = null
    }

    override fun checkEmptyName(fullname: String): Boolean {
        return fullname.isEmpty()
    }

    override fun checkValidName(fullname: String): Boolean{
      TODO("aun no implementado")
    }

    override fun chechEmptyLastname(lastname: String): Boolean {
        return lastname.isEmpty()
    }
    override fun checkValidEmail(email: String): Boolean {
        return PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()
    }
    override fun checkValidEmailDomain(email: String): Boolean{
        return email.endsWith("@gmail.com")
    }

    override fun checkValidPassword(password1: String, passworod2: String): Boolean {
        return password1.isEmpty() or passworod2.isEmpty()
    }

    override fun checkPasswordMatch(password1: String, passworod2: String): Boolean {
        return password1 == passworod2
    }

    override fun signUp(fullname: String, email: String,lastname: String, password1: String) {
        view?.Showprogress()
        registerInteractor?.signUp(fullname,email, password1, object: RegisterInteractor.registerCallBack{
            override fun onRegisterSucces() {
                val userId: String = mAuth.currentUser!!.uid
                writeNewUser(fullname, email, userId, lastname,"","")
                view?.navigateToMain()
                view?.HideProgress()
            }

            override fun onRegisterFailure(msgError: String) {
                view?.ShowError(msgError)
                view?.HideProgress()
            }

        })
    }
    data class User(val username: String? = null,val lastname: String, val email: String? = null, val phone: String, val CI: String) {
        // Null default values create a no-argument default constructor, which is needed
        // for deserialization from a DataSnapshot.
    }

    fun writeNewUser(fullname: String, email: String, userId: String, lastname: String, phone: String, CI: String) {
        val user = User(fullname, lastname, email, phone, CI)
        mDatabase.child("Users").child(userId).setValue(user)
    }

}