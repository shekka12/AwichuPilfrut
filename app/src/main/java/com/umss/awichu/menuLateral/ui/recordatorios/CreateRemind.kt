package com.umss.awichu.menuLateral.ui.recordatorios

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.umss.awichu.R
import kotlinx.android.synthetic.main.activity_create_remind.*
import kotlinx.android.synthetic.main.fragment_home.*
import java.time.Year

class CreateRemind : AppCompatActivity() {

    //Note
    //private lateinit var recyclerView: RecyclerView
    //private lateinit var fab: FloatingActionButton
    //

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_remind)

        editTextDateP.setOnClickListener{showDatePickerDialog()}

        editTextTime.setOnClickListener{showTimePickerDialog()}
    }

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
}