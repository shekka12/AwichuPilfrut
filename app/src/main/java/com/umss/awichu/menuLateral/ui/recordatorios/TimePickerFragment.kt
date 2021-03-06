package com.umss.awichu.menuLateral.ui.recordatorios

import android.app.Dialog
import android.app.TimePickerDialog
import android.content.Context
import android.os.Bundle
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.*

class TimePickerFragment (val listener:(String) -> Unit) : DialogFragment(),
    TimePickerDialog.OnTimeSetListener {


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c = Calendar.getInstance()
        val hour = c.get(Calendar.HOUR_OF_DAY)
        val minute = c.get(Calendar.MINUTE)
        val pickerTime = TimePickerDialog(activity as Context, this, hour, minute, true)

        return pickerTime
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        listener("$hourOfDay:$minute")
    }

}