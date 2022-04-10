package com.umss.awichu.presentation.authentification.registro.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import com.google.android.gms.dynamic.IFragmentWrapper
import com.umss.awichu.R
import com.umss.awichu.base.BaseActivity
import com.umss.awichu.presentation.authentification.registro.registerInteractor.registerInteractorImpl
import com.umss.awichu.presentation.authentification.login.view.MainActivity
import com.umss.awichu.presentation.main1.view.MainAwichuActivity
import com.umss.awichu.presentation.authentification.registro.RegisterContract
import com.umss.awichu.presentation.authentification.registro.presenter.RegisterPresenter
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : BaseActivity(), RegisterContract.registerView {


    lateinit var presenter: RegisterPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        presenter = RegisterPresenter(registerInteractorImpl())
        presenter.attachView(this)

        buttonRegistro.setOnClickListener {
            signUp()

        }

        buttonCancel.setOnClickListener {
            navigateToMain()
        }

    }

    override fun getLayout(): Int {
        return R.layout.activity_register
    }

    override fun navigateToMain() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags= Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)

    }

    override fun signUp() {
        val fullname:String = etx_fullname.text.toString().trim()
        val lastname:String = etx_lastname.text.toString().trim()
        val email:String = etx_emailRegistro.text.toString().trim()
        val password1:String = etx_passwordRegistro.text.toString().trim()
        val password2:String = etx_passwordRegistro2.text.toString().trim()

        if(presenter.checkEmptyName(fullname)){
            etx_fullname.error = "Ingrese un nombre"
            return
        }

        if(presenter.chechEmptyLastname(lastname)) {
            etx_lastname.error = "ingrese un apellido"
            return
        }
        if (!presenter.checkValidEmail(email)){
            etx_emailRegistro.error = "El correo es invalido"
            return
        }
        if (presenter.checkValidPassword(password1,password2)){
            etx_passwordRegistro.error = "Campo vacio"
            etx_passwordRegistro2.error = "Campo vacio"
            return
        }
        if (!presenter.checkPasswordMatch(password1,password2)){
            etx_passwordRegistro.error = "Las contraseñas no son iguales"
            etx_passwordRegistro2.error = "Las contraseñas no son iguales"
            return
        }
        presenter.signUp(fullname,email,password1)
    }

    override fun Showprogress() {
        progressSignUp.visibility = View.VISIBLE
        buttonRegistro.visibility = GONE
    }

    override fun HideProgress() {
        progressSignUp.visibility = View.GONE
        buttonRegistro.visibility = VISIBLE
    }

    override fun ShowError(msgError:String) {
        toast(this,msgError)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        presenter.detachView()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }
}