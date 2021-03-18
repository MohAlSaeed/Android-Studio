package com.example.broadcastreceiver

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.provider.DocumentsContract
import android.view.LayoutInflater
import android.widget.Adapter
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.broadcastreceiver.databinding.ActivityMainBinding
import com.example.broadcastreceiver.databinding.ActivityStockBinding
import kotlinx.android.synthetic.main.activity_stock.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Stock : AppCompatActivity() {

    private lateinit var receiver: PhoneReceiver
    private var id = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // here below you better write getLayoutInflater() at first which will be changed later into (layoutInflater)
        val binding = ActivityStockBinding.inflate(layoutInflater)
        setContentView(binding.root)

        receiver = PhoneReceiver()
        // what we need for teh recycle viewer :
        // LayoutManager
        // ItemDecorator
        // Adapter

        binding.srv1.layoutManager = LinearLayoutManager(this)  // layoutmanger
        binding.srv1.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL)) // ItemDecorator

        val phoneViewModel = PhoneViewModel(application)
        val adap = MyAdabter(this, phoneViewModel)
        binding.srv1.adapter = adap // the Adabter that will take our data and insert it into the itemvew

        // TODO observer on allphones list
        phoneViewModel.allPhones.observe(this, Observer { phones ->
            phones?.let {
                adap.setPhones(it)
            }
        })

        val intent1 = Intent(getString(R.string.add_phone))
        intent1.component = ComponentName("com.example.broadcastreceiver","com.example.broadcastreceiver.PhoneReceiver")
        binding.sbt1.setOnClickListener{

            var  phone = Phone(
                name = binding.stf1.text.toString() ,
                price = binding.stf2.text.toString().toInt(),
                quantity = binding.stf3.text.toString().toInt() ,
                available = binding.srbt1.isChecked
            )
            intent1.putExtra("phone_name", phone.name)

            CoroutineScope(Dispatchers.IO).launch {

                phoneViewModel.insert(phone)
            }
            intent1.putExtra("id", id++)
            sendBroadcast(intent1)
//            Toast.makeText(this,
//                "Phone: ${phone.name} Price: ${phone.price} quantity: ${phone.quantity} available: ${phone.available} was added",
//                Toast.LENGTH_SHORT
//            ).show()
        }
        binding.sbt1.setOnLongClickListener {
            CoroutineScope(Dispatchers.IO).launch {

               // // to go out the CoroutineScope and back again
                withContext(Dispatchers.Main){
                    binding.sbt1.setBackgroundColor(Color.RED)
                }
                phoneViewModel.deleteAll()
            }

            Toast.makeText(this,
                "All Phones database deleted",
                Toast.LENGTH_SHORT
            ).show()


            true
        }

    }

    override fun onStart() {
        super.onStart()
//        to make it dynemic regs
//        registerReceiver(receiver, IntentFilter(getString(R.string.add_phone)))
    }

    override fun onStop() {
        super.onStop()
//        to make it dynemic regs
//        unregisterReceiver(receiver)
    }
}