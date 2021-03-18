package com.example.stockfirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_menu.*

class Menu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        menubt1.setOnClickListener {
            startActivity(Intent(this, Stock::class.java))
        }

        menubt2.setOnClickListener {
            startActivity(Intent(this, Setting::class.java))
        }

        menubt3.setOnClickListener {
            startActivity(Intent(this,MapsActivity::class.java))
        }
    }
}