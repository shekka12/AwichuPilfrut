package com.umss.awichu.menuLateral.ui.slideshow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.umss.awichu.R
import com.umss.awichu.presentation.authentification.registro.presenter.RegisterPresenter
import kotlinx.android.synthetic.main.activity_edit_profil.*
import kotlinx.android.synthetic.main.activity_register.*



class EditProfil : AppCompatActivity() {

    val mAuth = FirebaseAuth.getInstance()
    val mDatabase = FirebaseDatabase.getInstance().reference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profil)
        Btn_saveData.setOnClickListener {
            editAll()
        }
    }

    fun editAll() {
        val userId: String = mAuth.currentUser!!.uid
        val fullname: String = text_slideshow.text.toString().trim()
        val lastname: String = text_slideshow2.text.toString().trim()
        val phoeEdit: String = text_slideshow3.text.toString().trim()
        val CIedit: String = text_slideshow4.text.toString().trim()
        writeNewUser(fullname,"", userId,lastname,phoeEdit, CIedit)

    }

    private fun writeNewUser(fullname: String, email: String, userId: String, lastname: String, phone: String, CIedit: String) {
        val user = RegisterPresenter.User(fullname, lastname, email, phone, CIedit)
        mDatabase.child("Users").child(userId).setValue(user)
    }

    data class User(val username: String? = null, val lastname: String, val email: String, val phone: String, val CI: String) {
        // Null default values create a no-argument default constructor, which is needed
        // for deserialization from a DataSnapshot.
    }


}