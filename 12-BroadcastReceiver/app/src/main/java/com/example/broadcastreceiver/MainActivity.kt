package com.example.broadcastreceiver

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.broadcastreceiver.databinding.ActivityMainBinding
import com.example.broadcastreceiver.databinding.ActivityStockBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sintent: Intent = Intent(this, Stock::class.java)
        fbt1.setOnClickListener(){
        startActivity(sintent)
        }
    }
}