package com.example.stockfirebase

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_stock.*

class Stock : AppCompatActivity() {

    lateinit var db : FirebaseDatabase
    lateinit var ref : DatabaseReference
    lateinit var auth: FirebaseAuth
    lateinit var phoneList:MutableList<Phone>
    lateinit var phoneListtarg:MutableList<Phone>
    lateinit var email: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stock)
        db = FirebaseDatabase.getInstance()
        ref = db.getReference("PhonesCollection")
        auth = FirebaseAuth.getInstance()
        email = auth.currentUser?.email.toString()

        stockbt.setOnClickListener {
            addphone()
        }

        stockbt.setOnLongClickListener {
            deleteAll()
            true
        }
        stocksw.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked == true){
                // todo
                ref.addValueEventListener(object : ValueEventListener{
                    override fun onDataChange(snapshot: DataSnapshot) {
                        if (snapshot!!.exists()){
                            phoneList.clear()
                            phoneListtarg.clear()
                            for (e in snapshot.children){
                                val phone = e.getValue(Phone::class.java)
                                if (phone?.email == email){
                                    phoneListtarg.add(phone!!)
                                }
                            }
                            val adap = PhoneAdapter(phoneListtarg)
                            stockrv.adapter = adap
                        } else {
                            phoneList.clear()
                            phoneListtarg.clear()
                            val adap = PhoneAdapter(phoneListtarg)
                            stockrv.adapter = adap
                        }
                    }
                    override fun onCancelled(error: DatabaseError) {
                    }
                })
            } else {
                // todo
                ref.addValueEventListener(object : ValueEventListener{
                    override fun onDataChange(snapshot: DataSnapshot) {
                        if (snapshot!!.exists()){
                            phoneList.clear()
                            phoneListtarg.clear()
                            for (e in snapshot.children){
                                val phone = e.getValue(Phone::class.java)
                                phoneList.add(phone!!)
                            }
                            val adap = PhoneAdapter(phoneList)
                            stockrv.adapter = adap
                        } else {
                            phoneList.clear()
                            phoneListtarg.clear()
                            val adap = PhoneAdapter(phoneList)
                            stockrv.adapter = adap
                        }
                    }
                    override fun onCancelled(error: DatabaseError) {
                    }
                })
            }
        }



        // our recycleView (stockrv) need the below
        // LayoutManager
        // ItemDeco
        // Adapter which needs a data to be passed in as array
        stockrv.layoutManager = LinearLayoutManager(this)
        stockrv.addItemDecoration(DividerItemDecoration(this,DividerItemDecoration.VERTICAL))

        phoneList = mutableListOf()
        phoneListtarg = mutableListOf()
//        val data = arrayListOf<String>("E1","E2","E3","E4","E5")
//        phoneList.add(Phone("1","Moh", 12,33, true))
//        phoneList.add(Phone("2","Moh2", 122,333, false))
//        val adap = PhoneAdapter(phoneList)
//        stockrv.adapter = adap



        // now we will get the data from the db and add an eventListener
        ref.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot!!.exists()){
                    phoneList.clear()
                    for (e in snapshot.children){
                        val phone = e.getValue(Phone::class.java)
                        phoneList.add(phone!!)
                    }
                    val adap = PhoneAdapter(phoneList)
                    stockrv.adapter = adap
                } else {
                    phoneList.clear()
                    val adap = PhoneAdapter(phoneList)
                    stockrv.adapter = adap
                }
            }
            override fun onCancelled(error: DatabaseError) {
            }
        })
    }











    private fun deleteAll() {
        val db = FirebaseDatabase.getInstance().getReference("PhonesCollection")
        db.removeValue()
    }


    // here we add a new Phone
    private fun addphone() {
        val db = FirebaseDatabase.getInstance().getReference("PhonesCollection")
        val id = db.push().key
        val name = stocktf1.text.toString()
        val price = stocktf2.text.toString().toInt()
        val quantity = stocktf3.text.toString().toInt()
        val available = stockcb.isChecked
        val phone = Phone(id!!, name, price, quantity, available, email)
        db.child(id).setValue(phone).addOnCompleteListener {
            Toast.makeText(this, "Phone added", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Toast.makeText(this, "Phone not added", Toast.LENGTH_SHORT).show()
        }
    }
}