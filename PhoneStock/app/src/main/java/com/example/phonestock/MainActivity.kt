package com.example.phonestock

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.phonestock.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_stock.view.*

class MainActivity : AppCompatActivity() {

    private lateinit var sp: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sp = getSharedPreferences("sizcol",Context.MODE_PRIVATE)
        val siz = sp.getFloat("siz", 10F)
        val col = sp.getInt("col", Color.BLACK)
        val col2 = sp.getInt("col2", Color.WHITE)
        ftv1.textSize = siz
        ftv1.setTextColor(col)
        binding.root.rootView.setBackgroundColor(col2)

        fbt1.setOnClickListener(){
            val intent1 = Intent(this, Stock::class.java)
            startActivity(intent1)
        }

        fbt2.setOnClickListener(){
            val intent2 = Intent(this, Setting::class.java)
            startActivity(intent2)
        }

    }

    override fun onStart() {
        super.onStart()
        val siz = sp.getFloat("siz", 10F)
        val col = sp.getInt("col", Color.BLACK)
        val col2 = sp.getInt("col2", Color.WHITE)
        ftv1.textSize = siz
        ftv1.setTextColor(col)
        f.rootView.setBackgroundColor(col2)
    }

}