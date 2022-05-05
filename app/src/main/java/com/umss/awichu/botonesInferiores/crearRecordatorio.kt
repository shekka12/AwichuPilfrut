package com.umss.awichu.botonesInferiores

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.Query
import com.google.firebase.ktx.Firebase
import com.umss.awichu.R
import com.umss.awichu.menuLateral.ui.recordatorios.*

class crearRecordatorio : AppCompatActivity() {

    private lateinit var recyclerViewNote: RecyclerView
    private lateinit var imageButton3: ImageButton

    private lateinit var noteDao: NoteDao
    private lateinit var auth: FirebaseAuth
    private lateinit var adaptor: RVAdaptor


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_boton_recordatorios)

        recyclerViewNote = findViewById(R.id.recyclerViewNote)
        imageButton3 = findViewById(R.id.imageButton3)

        noteDao = NoteDao()
        auth = Firebase.auth

        imageButton3.setOnClickListener{
            val intent = Intent(this,  CreateRemind::class.java)
            startActivity(intent)
        }

        setUpRecyclerView()

    }

    private fun setUpRecyclerView() {

        recyclerViewNote.layoutManager = LinearLayoutManager(this)


        val noteCollection = noteDao.noteCollection
        val currentUserId = auth.currentUser!!.uid

        //val query = noteCollection.whereEqualTo("uid",currentUserId).orderBy("text",Query.Direction.ASCENDING)
        val query = noteCollection.whereEqualTo("uid",currentUserId)

        val recyclerViewOption = FirestoreRecyclerOptions.Builder<Note>().setQuery(query,Note::class.java).build()

        adaptor = RVAdaptor(recyclerViewOption)
        recyclerViewNote.adapter = adaptor
    }

    override fun onStart() {
        super.onStart()
        adaptor.startListening()
    }

    override fun onStop() {
        super.onStop()
        adaptor.stopListening()
    }

}