package com.example.sharedpreferences

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {
    private lateinit var sp: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        sp = getSharedPreferences("sizcol",Context.MODE_PRIVATE)

        val a = sp.getFloat("siz", 10F)
        tv2.textSize = a

    }
    override fun onStart() {
        super.onStart()
        val a = sp.getFloat("siz", 10F)
        tv2.textSize = a
    }
}