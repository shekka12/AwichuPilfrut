package com.umss.awichu.presentation.authentification.passwordRecover.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

import com.umss.awichu.R
import com.umss.awichu.base.BaseActivity
import com.umss.awichu.domain.interactorsCasosDuso.authentification.passwordRecoverInteractor.passwordRecoverImpl
import com.umss.awichu.presentation.authentification.login.view.MainActivity
import com.umss.awichu.presentation.authentification.passwordRecover.exception.passwordRecoverException
import com.umss.awichu.presentation.authentification.passwordRecover.passwordRecoverContract
import com.umss.awichu.presentation.authentification.passwordRecover.presenter.passwordRecoverPresenter
import kotlinx.android.synthetic.main.activity_password_recover.*

class PasswordRecoverActivity : BaseActivity(), passwordRecoverContract.PasswordRecoverView {

    lateinit var presenter: passwordRecoverPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = passwordRecoverPresenter(passwordRecoverImpl())
        presenter.attachView(this)
        btn_recoverPassword.setOnClickListener {
            recoverPassword()
        }
    }


    override fun getLayout(): Int {
        return R.layout.activity_password_recover
    }

    override fun showError(msgError: String?) {
        toast(this, msgError)
    }

    override fun showProgress() {
        progress_recover_pw.visibility = View.VISIBLE
        btn_recoverPassword.visibility = View.GONE
    }

    override fun hideProgress() {
        progress_recover_pw.visibility = View.GONE
        btn_recoverPassword.visibility = View.VISIBLE
    }

    override fun recoverPassword() {
        val email = etx_recoverPassword.text.trim().toString()
        if (!email.isEmpty()){
            presenter.sendPasswordRecover(email)
        }else{
            toast(this,"Ingrese un correo")
        }
    }

    override fun navigationToLogin() {
        toast(this,"Se envió un a su correo un enlace para restablecer su contraseña")
        startActivity(Intent(this,MainActivity::class.java ))
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.dettachView()
        presenter.dettachJob()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        presenter.dettachView()
        presenter.dettachJob()
    }

}