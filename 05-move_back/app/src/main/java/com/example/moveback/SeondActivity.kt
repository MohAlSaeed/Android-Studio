package com.example.moveback

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_seond.*

class SeondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seond)
        // you can modify an Activity using Intent like below we are referring to the secondActivity
        // through intent2 where intent (Return the intent that started this activity.)
        val intent2 = this.intent
        val str = intent2.getStringExtra("str1")
        tv2.text = str
 
    }
}