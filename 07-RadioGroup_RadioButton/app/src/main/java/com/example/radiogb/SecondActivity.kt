package com.example.radiogb

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
        val str3 = intent2.getStringExtra("strc")
        tv2.text = str2
        tv2.textSize = 40F
        if (str3 != null) {
            tv2.setTextColor(str3.toInt())
        }
        Toast.makeText(this, str2, Toast.LENGTH_SHORT).show()
    }
}