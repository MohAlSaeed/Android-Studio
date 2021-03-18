package com.example.myfirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_employees_data.*
import kotlinx.android.synthetic.main.activity_main.*

class EmployeesDataActivity : AppCompatActivity() {

    lateinit var ref : DatabaseReference
    lateinit var emplyeeList:MutableList<Employees>
    lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employees_data)

        emplyeeList = mutableListOf()
        listView = lv1
        ref = FirebaseDatabase.getInstance().getReference("Employees")

        ref.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot!!.exists()){
                    emplyeeList.clear()
                    for (e in snapshot.children){
                        val employee = e.getValue(Employees::class.java)
                        emplyeeList.add(employee!!)
                    }
                    val adapter = EmployeeAdapter(this@EmployeesDataActivity,R.layout.employees,emplyeeList)
                    listView.adapter = adapter
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }

        })
    }
}