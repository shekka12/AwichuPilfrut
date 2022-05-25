package com.umss.awichu.presentation.authentification.login.view


import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.text.BoringLayout
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.CheckBox
import android.widget.Toast

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.umss.awichu.R
import com.umss.awichu.base.BaseActivity
import com.umss.awichu.domain.interactorsCasosDuso.authentification.loginInteractor.SignInInteractorImpl
import com.umss.awichu.menuLateral.MenuLateralActivity

import com.umss.awichu.presentation.authentification.login.LoginContract
import com.umss.awichu.presentation.authentification.login.presenter.LoginPresenter
import com.umss.awichu.presentation.authentification.passwordRecover.view.PasswordRecoverActivity

import com.umss.awichu.presentation.authentification.registro.view.RegisterActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.delay


/**creado por gabriel
 *
 */
class MainActivity : BaseActivity(), LoginContract.loginView {

    private var mIsShowPass = false

    lateinit var presenter: LoginContract.LoginPresenter
    val mAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ivHidePassword.setOnClickListener {
            mIsShowPass = !mIsShowPass
            showPassword(mIsShowPass)
        }

        showPassword(mIsShowPass)

        presenter = LoginPresenter(SignInInteractorImpl())
        presenter.attachView(this)


        btn_signIn.setOnClickListener {
            signIn()
        }
        buttonSignUp.setOnClickListener {
            navigateToRegister()
        }
        txt_recoverPassword.setOnClickListener{
            navigateToPasswordReset()
        }




    }

    private fun showPassword(isShow: Boolean) {
        if (isShow) {
            etx_password.transformationMethod = HideReturnsTransformationMethod.getInstance()
            ivHidePassword.setImageResource(R.drawable.ic_show_password_24)
        } else {
            etx_password.transformationMethod = PasswordTransformationMethod.getInstance()
            ivHidePassword.setImageResource(R.drawable.ic_hide_password_24)
        }
        etx_password.setSelection(etx_password.text.toString().length)
    }

    override fun getLayout(): Int {
        return R.layout.activity_main
    }

    override fun showError(msgError: String?) {
        toast(this, msgError)

    }

    override fun showProgressBar() {
       progressBar_singIn.visibility = View.VISIBLE
    }

    override fun hideProgreseDialog() {
        progressBar_singIn.visibility = View.GONE
    }

    override fun signIn() {
        //Toast.makeText(this, "prueba boton",Toast.LENGTH_SHORT).show()
        val email = etx_email.text.toString().trim()
        val password = etx_password.text.toString().trim()
        if (presenter.checkEmptyEmail(email) and presenter.checkEmptyPassword(password)) {
            toast(this, "ambos campos estan vacios")
        } else{
            if (presenter.checkEmptyEmail(email)) {
                toast(this, "ingrese un correo")

            } else {
                if (presenter.checkEmptyPassword(password)){
                    toast(this, "ingrese una contraseña")
                }
                else{
                    presenter.signInUserWithEmailandPassword(email, password, this)
                    clear()
                }

            }
        }
    }
    private fun clear(){
        etx_email.setText("")
        etx_password.setText("")
    }
    override fun navigateMain() {
        val intent = Intent(this,MenuLateralActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }

    override fun navigateToRegister() {
       startActivity(Intent(this, RegisterActivity::class.java))
    }

    override fun navigateToPasswordReset() {
        startActivity(Intent(this, PasswordRecoverActivity::class.java))
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




    private fun sesionActiva(){
        val user = Firebase.auth.currentUser
        if(user != null ){

            startActivity(Intent(this, MenuLateralActivity::class.java))
            finish()
        }
    }

    override fun onStart() {
        super.onStart()
        sesionActiva()

    }

}