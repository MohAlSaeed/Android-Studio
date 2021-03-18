package com.example.phonestock

import android.content.*
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.phonestock.databinding.ActivityMainBinding
import androidx.lifecycle.Observer
import com.example.phonestock.databinding.ActivityStockBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_stock.*
import kotlinx.android.synthetic.main.activity_stock.view.*
import kotlinx.android.synthetic.main.list_element.*
import kotlinx.android.synthetic.main.list_element.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Stock : AppCompatActivity() {

    private lateinit var sp: SharedPreferences
    private lateinit var edt: SharedPreferences.Editor
    private lateinit var myint: Intent
    private lateinit var receiver: PhoneReceiver
    private lateinit var receiver2: DeleteAllReceiver
    private var id = 0
    private var id2 = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityStockBinding = ActivityStockBinding.inflate(layoutInflater)
        setContentView(binding.root)

        receiver = PhoneReceiver()
        receiver2 = DeleteAllReceiver()
        sp = getSharedPreferences("sizcol",Context.MODE_PRIVATE)
        val siz = sp.getFloat("siz", 10F)
        val col = sp.getInt("col", Color.BLACK)
        val col2 = sp.getInt("col2", Color.WHITE)


        myint = this.intent
            binding.root.rootView.textView.textSize = siz
            binding.root.rootView.textView.setTextColor(col)
            binding.root.rootView.setBackgroundColor(col2)

        binding.root.rv1.layoutManager = LinearLayoutManager(this)
        binding.root.rv1.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )
        val phoneViewModel = PhoneViewModel(application)
        //      sending the SharedPreferences
        //      sending the SharedPreferences
        val adab = PhoneAdaptor(this, phoneViewModel, sp)
        binding.root.rv1.adapter = adab

        // TODO observer on allphones list
        phoneViewModel.allPhones.observe(this, Observer { phones ->
            phones?.let {
                adab.setPhones(it)
            }
        })

        val intentReceiver1 = Intent(getString(R.string.add_phone))
        intentReceiver1.component = ComponentName("com.example.phonestock","com.example.phonestock.PhoneReceiver")
        val intentReceiver2 = Intent(getString(R.string.delete_phone))
        intentReceiver2.component = ComponentName("com.example.phonestock","com.example.phonestock.DeleteAllReceiver")

//        you need to add two more to make another Toast message


        binding.root.bt1.setOnClickListener {
            val phone = Phone(
                name = binding.root.tf1.text.toString(),
                price = binding.root.tf2.text.toString().toInt(),
                quantity = binding.root.tf3.text.toString().toInt(),
                available = binding.root.cb1.isChecked
            )

            // adding intent for receiver
            intentReceiver1.putExtra("phone_name",phone.name)
            intentReceiver1.putExtra("id",id++)
//            intentReceiver.putExtra("id",id++)
            // add CoroutineScope to make it a multi thread
            CoroutineScope(Dispatchers.IO).launch {
                withContext(Dispatchers.Main){
                    binding.bt1.setBackgroundColor(Color.BLUE)
                }
                phoneViewModel.insert(phone)
            }
            sendBroadcast(intentReceiver1)
        }
        binding.bt1.setBackgroundColor(Color.BLUE)
        binding.root.bt1.setOnLongClickListener {
            CoroutineScope(Dispatchers.IO).launch {

                // to exit CoroutineScope to Main and back again
                withContext(Dispatchers.Main){
                    binding.bt1.setBackgroundColor(Color.RED)
                }
                phoneViewModel.deleteAll()
            }
            intentReceiver2.putExtra("phone_Remove","All List Removed")
            intentReceiver2.putExtra("id",id2++)
            sendBroadcast(intentReceiver2)
            true
        }
    }
    override fun onStart() {
        super.onStart()
        val siz = sp.getFloat("siz", 10F)
        val col = sp.getInt("col", Color.BLACK)
        val col2 = sp.getInt("col2", Color.WHITE)
        s.rootView.textView.textSize = siz
        s.rootView.textView.setTextColor(col)
        s.rootView.setBackgroundColor(col2)
//        registerReceiver(receiver, IntentFilter(getString(R.string.add_phone)))
//        registerReceiver(receiver2, IntentFilter(getString(R.string.delete_phone)))
    }
    override fun onStop() {
        super.onStop()
//        unregisterReceiver(receiver)
//        unregisterReceiver(receiver2)
    }
}