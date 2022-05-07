package com.umss.awichu.presentation.authentification.passwordRecover.presenter

import android.content.Context
import com.umss.awichu.domain.interactorsCasosDuso.authentification.passwordRecoverInteractor.passwordRecover
import com.umss.awichu.domain.interactorsCasosDuso.authentification.passwordRecoverInteractor.passwordRecoverImpl
import com.umss.awichu.presentation.authentification.passwordRecover.exception.passwordRecoverException
import com.umss.awichu.presentation.authentification.passwordRecover.passwordRecoverContract
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class passwordRecoverPresenter(paswordRecoverInteractor: passwordRecover): passwordRecoverContract.PasswordRecoverPresenter, CoroutineScope {

    var view:passwordRecoverContract.PasswordRecoverView? = null
    val job = Job()
    var paswordRecoverInteractor: passwordRecover? = null

    init{
        this.paswordRecoverInteractor = paswordRecoverInteractor
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job


    override fun attachView(passwordRecoverView: passwordRecoverContract.PasswordRecoverView) {
        this.view = passwordRecoverView
    }

    override fun dettachView() {
        view = null
    }

    override fun dettachJob() {
        coroutineContext.cancel()
    }

    override fun isViewAttached() :Boolean {
        return view != null
    }

    override fun sendPasswordRecover(email: String, context: Context) {
        launch {
            try {
                view?.showProgress()
                paswordRecoverInteractor?.sendPassWordResetEmail(email, context)
                view?.hideProgress()
                view?.navigationToLogin()
            }catch (e:passwordRecoverException){
                view?.hideProgress()
            }
        }
    }


}