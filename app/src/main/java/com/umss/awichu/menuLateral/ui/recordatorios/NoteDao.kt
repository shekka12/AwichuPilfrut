package com.umss.awichu.menuLateral.ui.recordatorios

import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class NoteDao {

     private val db = FirebaseFirestore.getInstance() // get db Instance
    //val db = Firebase.firestore // get db Instance
    val noteCollection = db.collection("Notes") // we create notes collections
    //we also need auth for currenUsesId
    private val mAuth = Firebase.auth

    fun addNote(text: String){
        val currentUserId = mAuth.currentUser!!.uid
        val note = Note(text, currentUserId)
        noteCollection.document().set(note)
    }

}