package com.example.recycleviewfirebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // for recycleviewer we need
        // 1- Layoutmanager
        // 2- ItemDecorator
        // 3- Adapter ,, in adapt the data to the layout of each element inside our list

        rv1.layoutManager = LinearLayoutManager(this)
        rv1.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        val data = arrayListOf<String>("Moh", "Mws", "Thu", "Dan", "Bas")
        rv1.adapter = MyAdapter(data)
    }
}