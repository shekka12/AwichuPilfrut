package com.umss.awichu.presentation.authentification.login.presenter

import com.umss.awichu.domain.interactorsCasosDuso.authentification.loginInteractor.SignInInteractor
import com.umss.awichu.presentation.authentification.login.LoginContract
import com.umss.awichu.presentation.authentification.login.exception.FirebaseLoginException
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class LoginPresenter(signInInteractor: SignInInteractor): LoginContract.LoginPresenter, CoroutineScope{
    //view.navigatetoLogin
    //view.showError
    var view: LoginContract.loginView? = null
    var signInInteractor: SignInInteractor? = null

    private val job = Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    init {
        this.signInInteractor = signInInteractor
    }

    override fun attachView(view: LoginContract.loginView) {
        this.view = view
    }

    override fun dettachView() {
        view = null
    }
    override fun dettachJob(){
        coroutineContext.cancel()
    }

    override fun isVIewAtached(): Boolean {
        return view != null
    }

    override fun signInUserWithEmailandPassword(email: String, passworw: String) {

        launch {
            view?.showProgressBar()
            try{
                signInInteractor?.SignInWithEmailAndPassword(email, passworw)
                if (isVIewAtached()){
                     view?.hideProgreseDialog()
                     view?.navigateMain()
                     }
            }catch (e: FirebaseLoginException){
                view?.showError(e.message)
                view?.hideProgreseDialog()
            }
        }


    }

    override fun checkEmptyEmail(email: String): Boolean {
        return email.isEmpty()
    }

    override fun checkEmptyPassword(passworw: String): Boolean {
        return  passworw.isEmpty()
    }

    override fun checkValidEmailLogin(email: String): Boolean {
        TODO("Not yet implemented")
    }


}