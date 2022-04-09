package com.umss.awichu.presentation.authentification.passwordRecover

import android.provider.ContactsContract.CommonDataKinds.Email

interface passwordRecoverContract {

    interface PasswordRecoverView{
        fun showError(msgError: String?)
        fun showProgress()
        fun hideProgress()
        fun recoverPassword()
        fun navigationToLogin()
    }
    interface PasswordRecoverPresenter{
        fun attachView(passwordRecoverActivity: PasswordRecoverView)
        fun dettachView()
        fun dettachJob()
        fun isViewAttached(): Boolean
        fun sendPasswordRecover(email: String)



    }
}