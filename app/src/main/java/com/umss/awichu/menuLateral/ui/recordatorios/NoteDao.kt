package com.umss.awichu.menuLateral.ui.recordatorios

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class NoteDao {

    var mDatabase = FirebaseDatabase.getInstance().reference
    private val mAuth = Firebase.auth

    fun addNote(text: String, fechaRecordatorio: String, hora: String, nombre: String){
        val currentUserId = mAuth.currentUser!!.uid
        val note = Note(text, currentUserId, fechaRecordatorio, hora, nombre)
        mDatabase.child("ListaDeRecordatorios").child(currentUserId).child(nombre).setValue(note)
    }

}