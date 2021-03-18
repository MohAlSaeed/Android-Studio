package com.example.sharedpreferences

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_second.*

class MainActivity : AppCompatActivity() {

    private lateinit var sp: SharedPreferences
    private lateinit var edt: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sp = getSharedPreferences("sizcol",Context.MODE_PRIVATE)
        edt = sp.edit()

        val myint = Intent(this, SecondActivity::class.java)
        bt.setOnClickListener{
            val a = tf.text.toString().toFloat()
            edt.putFloat("siz",a)
            edt.apply()
            startActivity(myint)
        }
    }



}