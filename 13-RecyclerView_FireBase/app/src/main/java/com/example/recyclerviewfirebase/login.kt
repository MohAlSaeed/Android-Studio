package com.example.recyclerviewfirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class login : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()


        // for registration part
        bt1.setOnLongClickListener {

            auth.createUserWithEmailAndPassword(tf1.text.toString(),tf2.text.toString()).addOnCompleteListener {
                if (it.isSuccessful){
                    Toast.makeText(this,"Registration success!", Toast.LENGTH_SHORT).show()
                }else {
                    Toast.makeText(this, "Registration failure!", Toast.LENGTH_SHORT ).show()
                }
            }

            true
        }

        // for creating a new account part
        bt1.setOnClickListener {

            auth.signInWithEmailAndPassword(tf1.text.toString(),tf2.text.toString()).addOnCompleteListener {
                if (it.isSuccessful){
                    Toast.makeText(this,"Login success!", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, MainActivity::class.java))
                }else {
                    Toast.makeText(this, "Login failure!", Toast.LENGTH_SHORT ).show()
                }
            }

            true
        }

    }
}