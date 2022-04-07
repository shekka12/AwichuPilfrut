package com.umss.awichu.presentation.registro.presenter

import androidx.core.util.PatternsCompat
import com.umss.awichu.domain.interactorsCasosDuso.registerInteractor.RegisterInteractor
import com.umss.awichu.presentation.registro.RegisterContract

class RegisterPresenter(registerInteractor:RegisterInteractor): RegisterContract.registerPresenter {

    var view:RegisterContract.registerView? = null
    var registerInteractor:RegisterInteractor? = null
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

    override fun checkValidEmail(email: String): Boolean {
        return PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()
    }


    override fun checkValidPassword(password1: String, passworod2: String): Boolean {
        return password1.isEmpty() or passworod2.isEmpty()
    }

    override fun checkPasswordMatch(password1: String, passworod2: String): Boolean {
        return password1 == passworod2
    }

    override fun signUp(fullname: String, email: String, password1: String) {
        view?.Showprogress()
        registerInteractor?.signUp(fullname,email, password1, object:RegisterInteractor.registerCallBack{
            override fun onRegisterSucces() {
                view?.navigateToMain()
                view?.HideProgress()
            }

            override fun onRegisterFailure(msgError: String) {
                view?.ShowError(msgError)
                view?.HideProgress()
            }

        })
    }

}