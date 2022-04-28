package com.umss.awichu.presentation.authentification.registro.view

import android.content.Intent
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import com.google.android.gms.dynamic.IFragmentWrapper
import com.umss.awichu.R
import com.umss.awichu.base.BaseActivity
import com.umss.awichu.menuLateral.MenuLateralActivity
import com.umss.awichu.presentation.authentification.registro.registerInteractor.registerInteractorImpl
import com.umss.awichu.presentation.authentification.login.view.MainActivity
import com.umss.awichu.presentation.main1.view.MainAwichuActivity
import com.umss.awichu.presentation.authentification.registro.RegisterContract
import com.umss.awichu.presentation.authentification.registro.presenter.RegisterPresenter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : BaseActivity(), RegisterContract.registerView {

    private var mIsShowPass = false

    lateinit var presenter: RegisterPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        ivHidePassword1.setOnClickListener {
            mIsShowPass = !mIsShowPass
            showPassword(mIsShowPass)
        }

        showPassword(mIsShowPass)

        ivHidePassword2.setOnClickListener {
            mIsShowPass = !mIsShowPass
            showPassword2(mIsShowPass)
        }

        showPassword2(mIsShowPass)

        presenter = RegisterPresenter(registerInteractorImpl())
        presenter.attachView(this)

        buttonRegistro.setOnClickListener {
            signUp()

        }

        buttonCancel.setOnClickListener {
            navigateToLogin()
        }

    }

    private fun showPassword(isShow: Boolean) {
        if (isShow) {
            etx_passwordRegistro.transformationMethod = HideReturnsTransformationMethod.getInstance()
            ivHidePassword1.setImageResource(R.drawable.ic_show_password_24)
        } else {
            etx_passwordRegistro.transformationMethod = PasswordTransformationMethod.getInstance()
            ivHidePassword1.setImageResource(R.drawable.ic_hide_password_24)
        }
        etx_passwordRegistro.setSelection(etx_passwordRegistro.text.toString().length)
    }

    private fun showPassword2(isShow: Boolean) {
        if (isShow) {
            etx_passwordRegistro2.transformationMethod = HideReturnsTransformationMethod.getInstance()
            ivHidePassword2.setImageResource(R.drawable.ic_show_password_24)
        } else {
            etx_passwordRegistro2.transformationMethod = PasswordTransformationMethod.getInstance()
            ivHidePassword2.setImageResource(R.drawable.ic_hide_password_24)
        }
        etx_passwordRegistro2.setSelection(etx_passwordRegistro2.text.toString().length)
    }

    override fun getLayout(): Int {
        return R.layout.activity_register
    }

    override fun navigateToMain() {
        val intent = Intent(this, MenuLateralActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

    override fun navigateToLogin() {
        val intentLogin = Intent(this, MainActivity::class.java)
        intentLogin.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intentLogin)
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
        }else{
            if(fullname.length < 2 && fullname.length >= 1) {
                etx_fullname.error = "El nombre debe tener entre 2 a 14 letras"
                return
            }
        }


        if(presenter.chechEmptyLastname(lastname)) {
            etx_lastname.error = "ingrese un apellido"
            return
        }else{
            if(lastname.length < 2 && lastname.length >= 1) {
                etx_lastname.error = "El apellido debe tener entre 2 a 14 letras"
                return
            }
        }

        if (!presenter.checkValidEmail(email)){
            etx_emailRegistro.error = "El correo es invalido"
            return
        }
        if(!presenter.checkValidEmailDomain(email)){
            etx_emailRegistro.error = "El correo debe tener dominio @gmail.com"
            return
        }
        if (presenter.checkValidPassword(password1,password2)){
            etx_passwordRegistro.error = "Campo vacio"
            etx_passwordRegistro2.error = "Campo vacio"
            return
        }else{
            if(password1.length < 8) {
                etx_passwordRegistro.error = "La contraseña debe tener al menos 8 caracteres"
                return
            }
        }

        if (!presenter.checkPasswordMatch(password1,password2)){
            etx_passwordRegistro.error = "Las contraseñas no coinciden"
            etx_passwordRegistro2.error = "Las contraseñas no coinciden"
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