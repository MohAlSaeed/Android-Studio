package com.example.firebaselogin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_stock.*

private const val TAG: String = "ERROR CHECK"
class Stock : AppCompatActivity() {

    private val firebaseRepo : FirebaseRepo = FirebaseRepo()
    private var phonelist: ArrayList<Phone> = arrayListOf()
//            listOf<Phone> = ArrayList<Phone>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stock)


        if (firebaseRepo.getUser() == null) {
            Toast.makeText(this, "Not Auth!", Toast.LENGTH_SHORT).show()
        } else {
//             user logged in
            loadData()



        rv.layoutManager = LinearLayoutManager(this)
        rv.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        // Adapter
            val myAdapter = MyAdapter(phonelist)
//        val data = arrayListOf<String>("Moh", "Thu", "Bas", "Dan")
//        val adap = MyAdapter(data)
        rv.adapter = myAdapter

        bt2.setOnClickListener {
            val phone = Phone(
                name= tf3.text.toString(),
                price = tf4.text.toString().toInt(),
                quantity = tf5.text.toString().toInt(),
                available = cb.isChecked
            )

            }
        }
    }

    private fun loadData() {
        firebaseRepo.getallphones().addOnCompleteListener {
            if (it.isSuccessful){
                phonelist = it.result!!.toObjects(Phone::class.java) as ArrayList<Phone>
            } else {
                Log.d(TAG, "Error: ${it.exception!!.message}")
            }
        }
    }
}