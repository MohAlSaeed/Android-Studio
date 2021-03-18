package com.example.phonestock

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.phonestock.databinding.ActivityStockBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_stock.*
import kotlinx.android.synthetic.main.activity_stock.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class Stock : AppCompatActivity() {

    private lateinit var sp: SharedPreferences
    private lateinit var edt: SharedPreferences.Editor
    private lateinit var myint: Intent
    private lateinit var dbf : FirebaseDatabase
//    private lateinit var phone_ref : DatabaseReference
//    private var id3: Long = 0
//    private lateinit var receiver: PhoneReceiver
//    private lateinit var receiver2: DeleteAllReceiver
    private var id = 0
    private var id2 = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityStockBinding = ActivityStockBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        receiver = PhoneReceiver()
//        receiver2 = DeleteAllReceiver()
        sp = getSharedPreferences("sizcol", Context.MODE_PRIVATE)
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

        dbf = FirebaseDatabase.getInstance()
        val ref = dbf.getReference("objects")
        val phone_ref = ref.child("Phones")
        val phone_list = arrayListOf<Phone>()
        val phoneViewModel = PhoneViewModel(application, phone_ref, phone_list)
        //      sending the SharedPreferences
        //      sending the SharedPreferences
//        val adab = PhoneAdaptor(this, phoneViewModel, sp)
        val adab = PhoneAdaptor(this, phoneViewModel, sp)

        binding.root.rv1.adapter = adab

        // TODO observer on allphones list
        phoneViewModel.allPhones.observe(this, Observer { phones ->
            phones?.let {
                adab.setPhones(it)
            }
        })

//        val intentReceiver1 = Intent(getString(R.string.add_phone))
//        intentReceiver1.component = ComponentName("com.example.phonestock","com.example.phonestock.PhoneReceiver")
//        val intentReceiver3 = Intent("com.example.broadcast.ADD_PHONEOUT")
//        intentReceiver3.component = ComponentName("com.example.broadcast","com.example.broadcast.MyReceiverOut")
        val intentReceiver3 = Intent("com.example.broadcast.ADD_PHONEOUT")
        intentReceiver3.setClassName("com.example.broadcast", "com.example.broadcast.MyReceiverOut")
        

//        val intentReceiver4 = Intent("com.example.app2.ADD_PHONE4")
//        intentReceiver4.component = ComponentName("com.example.app2","com.example.app2.BReceiver")
        val intentReceiver2 = Intent(getString(R.string.delete_phone))
        intentReceiver2.component = ComponentName(
            "com.example.phonestock",
            "com.example.phonestock.DeleteAllReceiver"
        )


        // Write a message to the database
        // Write a message to the database
        // Write a message to the database
//        dbf = FirebaseDatabase.getInstance()
//        val ref = dbf.getReference("objects")
//        val phone_ref = ref.child("Phones")
//        val mysnap = phone_ref.database
//        val dataSnapshot =

//        val phones_list = arrayListOf<Phone>()

//        val ref = dbf.getReference("Stock")
//        val phone_ref = ref.child("Phone")
//        val phone_list = arrayListOf<Phone>()


        binding.root.bt1.setOnClickListener {
//            val phone2 = myPhone
            val phone = Phone(
                name = binding.root.tf1.text.toString(),
                price = binding.root.tf2.text.toString().toInt(),
                quantity = binding.root.tf3.text.toString().toInt(),
                available = binding.root.cb1.isChecked
            )
            CoroutineScope(Dispatchers.IO).launch {
                withContext(Dispatchers.Main){
                    binding.bt1.setBackgroundColor(Color.BLUE)
                }

                phone_ref.push().setValue(phone)
//                Log.i("Read", phone_ref.)

            }

//            intentReceiver.putExtra("id",id++)
            // add CoroutineScope to make it a multi thread
//            CoroutineScope(Dispatchers.IO).launch {
//                withContext(Dispatchers.Main){
//                    binding.bt1.setBackgroundColor(Color.BLUE)
//                }
//                phoneViewModel.insert(phone)
//            }
            // adding intent for receiver
            intentReceiver3.putExtra("phone_name", phone.name)
            intentReceiver3.putExtra("id", id++)
            sendBroadcast(intentReceiver3)
        }
        binding.bt1.setBackgroundColor(Color.BLUE)
        binding.root.bt1.setOnLongClickListener {
            CoroutineScope(Dispatchers.IO).launch {

                // to exit CoroutineScope to Main and back again
                withContext(Dispatchers.Main){
                    binding.bt1.setBackgroundColor(Color.RED)
                }
//                phoneViewModel.deleteAll()
            }
            intentReceiver2.putExtra("phone_Remove", "All List Removed")
            intentReceiver2.putExtra("id", id2++)
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