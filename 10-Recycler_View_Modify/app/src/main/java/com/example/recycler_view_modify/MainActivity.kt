package com.example.recycler_view_modify

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    // this part to save and read from the Shared Preferences
    private lateinit var sp:SharedPreferences
    private lateinit var edt:SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sp = getPreferences(Context.MODE_PRIVATE)
        edt = sp.edit()

        rv1.layoutManager = LinearLayoutManager(this)
        rv1.addItemDecoration(DividerItemDecoration(this,DividerItemDecoration.VERTICAL))
        val data = arrayListOf("Element 1 ","Element 2 ","Element 3 ","Element 4 ","Element 5 ","Element 6 ")
        val adab = MyAdabter(data)
        rv1.adapter = adab

        button1.setOnClickListener {
            adab.add(tf1.text.toString())
        }
    }

    override fun onStart() {
        super.onStart()
        tv1.text = sp.getString("str_sp", "ANDROID")
    }

    override fun onStop() {
        super.onStop()
        edt.putString("str_sp", tv1.text.toString())
        edt.apply()
    }

}