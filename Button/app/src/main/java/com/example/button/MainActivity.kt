package com.example.button

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import com.example.button.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        setContentView(R.layout.activity_main)

    }

    fun click(view: View) {
        // play1 :
//        tv1.text = getString(R.string.wlcome)
//        tv1.textSize = 30F
//        tv1.setTextColor(Red)
        // play 2 :
//        Log.i("MOMO", "this is Button clicked!")
        // play 3 :
        // dont forget the show()
        //Toast.makeText(this, "Hello MO!", Toast.LENGTH_SHORT).show()
        Toast.makeText(this, "Hello ThuThu!", Toast.LENGTH_SHORT).show()
    }
}