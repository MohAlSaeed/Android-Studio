package com.example.phonestock

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_menu.*

class Menu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        mbt1.setOnClickListener {
            startActivity(Intent(this, Stock::class.java))
        }

        mbt2.setOnClickListener {
            startActivity(Intent(this, Setting::class.java))
        }
    }
}