package com.example.sqllite

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sqllite.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
//    private lateinit var sp: SharedPreferences
//    private lateinit var edtsp: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



//        sp = getPreferences(Context.MODE_PRIVATE)
//        edtsp = sp.edit()

        binding.rv1.layoutManager = LinearLayoutManager(this)
        binding.rv1.addItemDecoration(DividerItemDecoration(this,DividerItemDecoration.VERTICAL))
        val phoneViewModel = PhoneViewModel(application)

        val adab = PhoneAdaptor(this,phoneViewModel)
        binding.rv1.adapter = adab
        phoneViewModel.allPhones.observe(this, Observer {
            phones -> phones?.let{
            adab.setPhones(it)
        }
        })

        binding.bt1.setOnClickListener {
            val phone = Phone(
                name = binding.tf1.text.toString(),
                price = binding.tf2.text.toString().toInt(),
                quantity = binding.tf3.text.toString().toInt(),
                available = binding.cb1.isChecked
            )
            phoneViewModel.insert(phone)
            Toast.makeText(this,
                "Phone : ${phone.name} ${phone.price} was added.",
                Toast.LENGTH_SHORT
            ).show()
        }
        binding.bt1.setOnLongClickListener {
            phoneViewModel.deleteAll()
            Toast.makeText(this,
                "All Phone records removed.",
                Toast.LENGTH_SHORT
            ).show()
            true
        }










//        var data = arrayListOf<String>("Element 1","Element 2","Element 3","Element 4","Element 5")
//        var adab = MyRycycleViewer(data)
//        rv1.adapter = adab

//        bt1.setOnClickListener{
//            adab.add(tf1.text.toString())
//        }
    }

//    override fun onStart() {
//        super.onStart()
//        tv.text = sp.getString("str", "ANDROID")
//    }
//
//    override fun onStop() {
//        super.onStop()
//        edtsp.putString("str", tv.text.toString())
//        edtsp.apply()
//    }

}