package com.umss.awichu.menuLateral.ui.recordatorios

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.umss.awichu.R
import com.umss.awichu.botonesInferiores.BotonRecordatorios
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_remain.*
import kotlinx.android.synthetic.main.activity_remain.editTextDateP
import kotlinx.android.synthetic.main.activity_remain.editTextTime
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import android.app.TimePickerDialog
import android.widget.TimePicker
import java.util.*

//import java.time.format.DateTimeFormatter


class RemainActivity : AppCompatActivity() {

    private lateinit var remainNote: NoteDao

    var timeformate = SimpleDateFormat("hh:mm a", Locale.ENGLISH)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_remain)

        editTextDateP.setOnClickListener{showDatePickerDialog()}
        //editTextTime.setOnClickListener{showTimePickerDialog()} //Ya no se emplea

        //Prueba time
        editTextTime.setOnClickListener{
            val now = Calendar.getInstance()
            val timePicker = TimePickerDialog (this, TimePickerDialog.OnTimeSetListener{ view, hourOfDay, minute ->
                val selectedTime = Calendar.getInstance()
                selectedTime.set(Calendar.HOUR_OF_DAY, hourOfDay)
                selectedTime.set(Calendar.MINUTE, minute)
                editTextTime.setText(timeformate.format(selectedTime.time))
            },
                now.get(Calendar.HOUR_OF_DAY), now.get(Calendar.MINUTE), true)

            timePicker.show()
        }
        //

        btn_saveRemain.setOnClickListener{
            createRemainUser()

        }

        btn_goToBack.setOnClickListener {
           navigateBackList()
        }
    }



    private fun showDatePickerDialog() {
        val datePicker = DatePickerFragment{day, month, year -> onDateSelected(day, month, year)}
        datePicker.show(supportFragmentManager, "datePicker")
    }

    fun onDateSelected(day:Int, month:Int, year: Int){
        editTextDateP.setText("$day/$month/$year")
    }

    private fun navigateBackList(){
        val intent = Intent(this, BotonRecordatorios::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }
    private fun createRemainUser(){

        val fecha = editTextDateP.text.toString().trim()
        val hora = editTextTime.text.toString().trim()
        val nombre = etx_title.text.toString().trim()
        val note = etx_NoteDescription.text.toString().trim()

        remainNote = NoteDao()

        if(fecha.isNotEmpty() && hora.isNotEmpty() && note.isNotEmpty()){
            remainNote.addNote(note, fecha, hora, nombre)
            Toast.makeText(this, "Recordatorio creado exitosamente", Toast.LENGTH_LONG).show()

        }
        else{
            Toast.makeText(this, "Los campos estan vacios", Toast.LENGTH_SHORT).show()
        }
        clear()
    }
    private fun clear(){
        editTextDateP.setText("")
        editTextTime.setText("")
        etx_title.setText("")
        etx_NoteDescription.setText("")
    }

    override fun onDestroy() {
        super.onDestroy()
        finish()
    }

}