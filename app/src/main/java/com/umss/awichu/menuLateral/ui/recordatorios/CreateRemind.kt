package com.umss.awichu.menuLateral.ui.recordatorios

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.umss.awichu.R
import com.umss.awichu.botonesInferiores.crearRecordatorio
import kotlinx.android.synthetic.main.activity_create_remind.*
import kotlinx.android.synthetic.main.fragment_home.*
import java.time.Year

class CreateRemind : AppCompatActivity() {

    //Note
    private lateinit var noteEditText: EditText
    private lateinit var addNoteButton: Button
    private  lateinit var buttonCancelRemind: Button
    private lateinit var noteDao: NoteDao
    //

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_remind)

        //DatePicker and TimePicker
        editTextDateP.setOnClickListener{showDatePickerDialog()}

        editTextTime.setOnClickListener{showTimePickerDialog()}
        //DatePicker and TimePicker

        //Add Note
        noteEditText = findViewById(R.id.noteEditText)
        addNoteButton = findViewById(R.id.addNoteButton)
        noteDao = NoteDao()
        //

        addNoteButton.setOnClickListener{
            val note = noteEditText.text.toString()
            if(note.isNotEmpty()){
                //Ir a crearRecordatorio
                //Ahora creamos la nota para añadirlo en firebase
                noteDao.addNote(note)
                val intent = Intent(this, crearRecordatorio::class.java)
                startActivity(intent)
            }
        }

        //buttonCancelRemind.setOnClickListener {
          //
        //}

    }

    //DatePicker and TimePicker
    private fun showTimePickerDialog() {
        val timePicker = TimePickerFragment {onTimeSelected(it)}

        timePicker.show(supportFragmentManager, "time")
    }

    private fun onTimeSelected(time:String){
        editTextTime.setText("$time")
    }

    private fun showDatePickerDialog() {
        val datePicker = DatePickerFragment{day, month, year -> onDateSelected(day, month, year)}
        datePicker.show(supportFragmentManager, "datePicker")
    }

    fun onDateSelected(day:Int, month:Int, year: Int){
        editTextDateP.setText("$day/$month/$year")
    }
    //DatePicker and TimePicker

}