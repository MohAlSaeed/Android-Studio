package com.example.saveintoactivity

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var sp: SharedPreferences
    private lateinit var myeditor: SharedPreferences.Editor


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // getSharedPreferences gets additional argument which indicate a specific file , because we could have a couple of files
        // if we have a lot of preferences of things to refer to
        sp = getPreferences(Context.MODE_PRIVATE)
        myeditor = sp.edit()
        bt1.setOnClickListener {
            tv1.text = tf1.text
        }
    }

    override fun onStart() {
        super.onStart()
        // read from shared preferences
        tv1.text = sp.getString("key_str1", "Nothing!")
    }

    override fun onStop() {
        super.onStop()
        // save to shared preferences
        myeditor.putString("key_str1",tv1.text.toString())
        myeditor.apply()
    }
}