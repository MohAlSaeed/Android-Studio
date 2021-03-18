package com.example.moveback

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
        tv1.text = tf1.text
        Toast.makeText(this, tf1.text, Toast.LENGTH_SHORT).show()
        // Intent means aim, target, goal, objective or target
        val intent1 = Intent(this, SeondActivity::class.java)
        intent1.putExtra("str1", tf1.text.toString())
//        intent.putExtra("str2", tf1.text.toString())
        startActivity(intent1)
    }
}