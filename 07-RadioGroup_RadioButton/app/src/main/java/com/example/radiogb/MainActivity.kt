package com.example.radiogb

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun click(view: View) {
        if (cb1.isChecked){
            val intent1 = Intent(this, SecondActivity::class.java)
            intent1.putExtra("str", tf1.text.toString())
            intent1.putExtra("strc", whatColor().toString())
            startActivity(intent1)
        } else {
            tv1.text = tf1.text
            tv1.setTextColor(whatColor())
            Toast.makeText(this, tf1.text, Toast.LENGTH_SHORT).show()
        }
    }

    fun whatColor(): Int {
        return when( findViewById<RadioButton>(rg1.checkedRadioButtonId).text){
            getString(R.string.red) -> Color.RED
            getString(R.string.blue) -> Color.BLUE
            getString(R.string.green) -> Color.GREEN
            else -> Color.BLACK
        }
    }
}