package com.example.phonestock

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()

        // for registration part
        button.setOnLongClickListener {

            auth.createUserWithEmailAndPassword(et_email.text.toString(),et_pass.text.toString()).addOnCompleteListener {
                if (it.isSuccessful){
                    Toast.makeText(this,"Registration success!", Toast.LENGTH_SHORT).show()
                }else {
                    Toast.makeText(this, "Registration failure!", Toast.LENGTH_SHORT ).show()
                }
            }

            true
        }

        // for creating a new account part
        button.setOnClickListener {

            auth.signInWithEmailAndPassword(et_email.text.toString(),et_pass.text.toString()).addOnCompleteListener {
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