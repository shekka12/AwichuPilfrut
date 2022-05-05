package com.umss.awichu.menuLateral.ui.recordatorios

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import java.util.*

class DatePickerFragment (val listener: (day:Int, month:Int, year:Int) -> Unit): DialogFragment(),
    DatePickerDialog.OnDateSetListener{


    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        listener(dayOfMonth, month, year)

    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val c = Calendar.getInstance()
        val day = c.get(Calendar.DAY_OF_MONTH)
        val month = c.get(Calendar.MONTH)
        val year = c.get(Calendar.YEAR)

        val picker = DatePickerDialog(activity as Context, this, year, month, day)

        c.set(Calendar.YEAR, year - 10)
        picker.datePicker.minDate = c.timeInMillis
        //c.set(Calendar.YEAR, year + 80) //Limite maximo para el datePicker
        //picker.datePicker.maxDate = c.timeInMillis

        return picker
    }


}