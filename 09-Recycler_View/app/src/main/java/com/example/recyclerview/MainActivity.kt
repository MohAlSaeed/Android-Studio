package com.example.recyclerview

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

    private lateinit var sp: SharedPreferences
    private lateinit var edit: SharedPreferences.Editor


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sp = getPreferences(Context.MODE_PRIVATE)
        edit = sp.edit()
        // you have two options to apply the below
//        bt1.setOnClickListener {
//            tv1.text = tf1.text
//        }
        // for the recycle view we will need
        // 1- layout manager
        // 20 Item Decorator
        // 3- Adapter
        rv1.layoutManager = LinearLayoutManager(this) // layout manager
        rv1.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL)) // this is a decorator it is our line between the items
        // Adapter
        val data = arrayListOf<String>("Element 1 ","Element 2 ","Element 3 ","Element 4 ","Element 5 ","Element 6 ")
        rv1.adapter = MyAdapter(data)

    }

    override fun onStart() {
        super.onStart()
        tv1.text = sp.getString("str_sp", "Nothing!")
    }

    override fun onStop() {
        super.onStop()
        edit.putString("str_sp", tv1.text.toString())
        edit.apply()
    }

    fun click(view: View) {
        tv1.text = tf1.text

    }
}