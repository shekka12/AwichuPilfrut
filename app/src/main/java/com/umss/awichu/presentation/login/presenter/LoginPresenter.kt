package com.umss.awichu.presentation.login.presenter

import com.umss.awichu.domain.interactorsCasosDuso.loginInteractor.SignInInteractor
import com.umss.awichu.presentation.login.LoginContract

class LoginPresenter(signInInteractor: SignInInteractor): LoginContract.LoginPresenter{
    //view.navigatetoLogin
    //view.showError
    var view:LoginContract.loginView? = null
    var signInInteractor:SignInInteractor? = null
    init {
        this.signInInteractor = signInInteractor
    }

    override fun attachView(view: LoginContract.loginView) {
        this.view = view
    }

    override fun dettachView() {
        view = null
    }

    override fun isVIewAtached(): Boolean {
        return view != null
    }

    override fun signInUserWithEmailandPassword(email: String, passworw: String) {
        view?.showProgressBar()
        signInInteractor?.SignInWithEmailAndPassword(email, passworw, object: SignInInteractor.signInCallBack{
            override fun onSignInSucces() {
                if (isVIewAtached()){
                    view?.hideProgreseDialog()
                    view?.navigateMain()
                }
            }

            override fun onSignInFailure(msgError: String) {
                if (isVIewAtached()){
                    view?.hideProgreseDialog()
                    view?.showError(msgError)
                }
            }
        })
    }



    override fun checkEmptyFields(email: String, passworw: String): Boolean {
        return email.isEmpty() || passworw.isEmpty()
    }


}