package com.example.intent_checkbox

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        val intent2 = this.intent
        val str2 = intent2.getStringExtra("str")
        tv2.text = str2
        Toast.makeText(this, str2, Toast.LENGTH_SHORT).show()
    }
}