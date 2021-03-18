package com.example.stockfirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()

        loginbt.setOnLongClickListener {
            auth.createUserWithEmailAndPassword(logintf1.text.toString(), logintf2.text.toString()).addOnSuccessListener {
                Toast.makeText(this, "User: ${logintf1.text.toString()} created", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(this, "User: ${logintf1.text.toString()} not added", Toast.LENGTH_SHORT).show()
            }
            true
        }

        loginbt.setOnClickListener {
            auth.signInWithEmailAndPassword(logintf1.text.toString(),logintf2.text.toString()).addOnSuccessListener {
                Toast.makeText(this, "User: ${logintf1.text.toString()} success login", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this,Menu::class.java))
            }.addOnFailureListener {
                Toast.makeText(this, "User: ${logintf1.text.toString()} failed login", Toast.LENGTH_SHORT).show()
            }
        }

    }
}