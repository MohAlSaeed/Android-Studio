package com.example.phonestock

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

        lbt1.setOnLongClickListener {
            auth.createUserWithEmailAndPassword(ltf1.text.toString(), ltf2.text.toString()).addOnCompleteListener {
                if (it.isSuccessful){
                    Toast.makeText(this,"Registration success!", Toast.LENGTH_SHORT).show()
                }else {
                    Toast.makeText(this, "Registration failure!", Toast.LENGTH_SHORT ).show()
                }
            }
            true
        }

        lbt1.setOnClickListener {
            auth.signInWithEmailAndPassword(ltf1.text.toString(), ltf2.text.toString()).addOnCompleteListener {
                if (it.isSuccessful){
                    Toast.makeText(this,"Login success!", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, Menu::class.java))
                }else {
                    Toast.makeText(this, "Login failure!", Toast.LENGTH_SHORT ).show()
                }
            }
        }
    }
}