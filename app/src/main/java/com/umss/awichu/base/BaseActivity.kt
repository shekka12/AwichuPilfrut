package com.umss.awichu.base

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity


abstract class BaseActivity:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(getLayout())
        super.onCreate(savedInstanceState)

    }

    @LayoutRes
    abstract fun getLayout():Int

    fun Context.toast(context: Context = applicationContext, message:String,duration: Int =Toast.LENGTH_SHORT){
        Toast.makeText(context, message,duration).show()
    }


}