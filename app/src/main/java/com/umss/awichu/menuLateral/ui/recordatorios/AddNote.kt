package com.umss.awichu.menuLateral.ui.recordatorios

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.umss.awichu.R

class AddNote : AppCompatActivity(){

    private lateinit var noteEditText: EditText
    private lateinit var addNoteButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_remind)
    }
}