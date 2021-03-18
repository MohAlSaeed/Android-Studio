package com.example.phonestock

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.SizeF
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.phonestock.databinding.ActivityMainBinding
import com.example.phonestock.databinding.ActivitySettingBinding
import com.example.phonestock.databinding.ActivityStockBinding
import kotlinx.android.synthetic.main.activity_setting.*

class Setting : AppCompatActivity() {
    private lateinit var sp: SharedPreferences
    private lateinit var edt: SharedPreferences.Editor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivitySettingBinding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sp = getSharedPreferences("sizcol",Context.MODE_PRIVATE)
        edt = sp.edit()

        val intent1 = Intent(this, MainActivity::class.java)

        binding.sbt1.setOnClickListener(){
            if (sw1.isChecked){
                edt.putFloat("siz", 15F)
            } else {
                edt.putFloat("siz", 10F)
            }

            if (sw2.isChecked){
                edt.putInt("col", Color.BLUE)
            } else {
                edt.putInt("col", Color.BLACK)
            }

            if (sw3.isChecked){
                edt.putInt("col2", Color.GREEN)
            } else {
                edt.putInt("col2", Color.WHITE)
            }
            edt.apply()

            startActivity(intent1)
        }

    }
}