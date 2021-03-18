package com.example.intent_checkbox

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun click(view: View) {
        if (cb.isChecked){
            val intent1 = Intent(this, SecondActivity::class.java)
            intent1.putExtra("str", tf1.text.toString())
            startActivity(intent1)
        }else{
            tv1.text = tf1.text
            Toast.makeText(this, tf1.text.toString(), Toast.LENGTH_SHORT).show()
        }


    }
}