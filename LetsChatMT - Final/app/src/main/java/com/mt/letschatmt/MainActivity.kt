package com.mt.letschatmt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var auth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        auth = FirebaseAuth.getInstance()
        lbt1.setOnLongClickListener {
            auth.createUserWithEmailAndPassword(ltf1.text.toString(), ltf2.text.toString()).addOnCompleteListener {
                Toast.makeText(this, "New User Created", Toast.LENGTH_SHORT).show()
            }
                .addOnFailureListener {
                    Toast.makeText(this, "Please use E-mail and Password more than 8 chars", Toast.LENGTH_SHORT).show()
                }

            true
        }

        lbt1.setOnClickListener {
            auth.signInWithEmailAndPassword(ltf1.text.toString(), ltf2.text.toString()).addOnCompleteListener {
                Toast.makeText(this, "Login success", Toast.LENGTH_SHORT).show()
            }
                .addOnFailureListener {
                    Toast.makeText(this, "E-mail or Password is incorrect", Toast.LENGTH_SHORT).show()
                }
            startActivity(Intent(this, Chat::class.java))
        }
        lbt2.setOnClickListener {
            startActivity(Intent(this, Setting::class.java))
        }
    }
}