package com.example.firebaselogin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()

        bt1.setOnLongClickListener {
            auth.createUserWithEmailAndPassword(tf1.text.toString(),tf2.text.toString()).addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(this,"Registration success!", Toast.LENGTH_SHORT).show()
                } else{
                    Toast.makeText(this, "Registration failure!", Toast.LENGTH_SHORT ).show()
                }
            }

            true
        }

        bt1.setOnClickListener {

            auth.signInWithEmailAndPassword(tf1.text.toString(),tf2.text.toString()).addOnCompleteListener {
                if (it.isSuccessful){
                    startActivity(Intent(this, Stock::class.java))
                    Toast.makeText(this,"Login success!", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this, "Login failure!", Toast.LENGTH_SHORT ).show()
                }
            }

        }

    }
}