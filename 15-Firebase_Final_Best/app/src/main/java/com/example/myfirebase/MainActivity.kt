package com.example.myfirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bt1.setOnClickListener {
            saveData()
        }

        bt2.setOnClickListener {
            startActivity(Intent(this,EmployeesDataActivity::class.java))
        }

    }

    private fun saveData() {
        val name = tf1.text.toString()
        val surname = tf2.text.toString()
        val age = tf3.text.toString().toInt()
        val phone = tf4.text.toString().toInt()

        val db = FirebaseDatabase.getInstance().getReference("Employees")
        val employeeId = db.push().key
        val employee = Employees(employeeId!!,name,surname,age,phone)
        db.child(employeeId).setValue(employee).addOnCompleteListener {
            Toast.makeText(this, "Emp Saved :) ", Toast.LENGTH_SHORT).show()
        }
    }
}