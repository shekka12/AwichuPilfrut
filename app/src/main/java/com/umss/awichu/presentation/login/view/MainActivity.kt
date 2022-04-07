package com.umss.awichu.presentation.login.view


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.umss.awichu.R
import com.umss.awichu.base.BaseActivity
import com.umss.awichu.domain.interactorsCasosDuso.loginInteractor.SignInInteractorImpl
import com.umss.awichu.presentation.login.LoginContract
import com.umss.awichu.presentation.login.presenter.LoginPresenter
import com.umss.awichu.presentation.main1.view.MainAwichuActivity
import com.umss.awichu.presentation.registro.view.RegisterActivity
import kotlinx.android.synthetic.main.activity_main.*

/**creado por gabriel
 *
 */
class MainActivity : BaseActivity(), LoginContract.loginView {

    lateinit var presenter:LoginContract.LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        presenter = LoginPresenter(SignInInteractorImpl())
        presenter.attachView(this)
        btn_signIn.setOnClickListener {
            signIn()
        }
        buttonSignUp.setOnClickListener {
            navigateToRegister()
        }

    }

    override fun getLayout(): Int {
        return R.layout.activity_main
    }

    override fun showError(msgError: String) {
        Toast.makeText(this,msgError,Toast.LENGTH_SHORT).show()
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
        if (presenter.checkEmptyFields(email, password))
            toast(this, "uno o ambos campos estan vacios")
        else {
            presenter.signInUserWithEmailandPassword(email, password)
        }
    }

    override fun navigateMain() {
        val intent = Intent(this,MainAwichuActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }

    override fun navigateToRegister() {
       startActivity(Intent(this, RegisterActivity::class.java))
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.dettachView()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        presenter.dettachView()
    }


}