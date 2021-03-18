package com.mt.letschatmt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_chat.*

class Chat : AppCompatActivity() {
    lateinit var auth : FirebaseAuth
    lateinit var db : FirebaseFirestore
    lateinit var adap: MainAdapter
    lateinit var datalistall: MutableList<MyData>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()
        val email = auth.currentUser?.email
        myrv.layoutManager = LinearLayoutManager(this)
        myrv.addItemDecoration(DividerItemDecoration(this,DividerItemDecoration.VERTICAL))

        datalistall =  mutableListOf()
        val mainViewModel = MainViewModel(application )
        adap = MainAdapter(this, mainViewModel)
        myrv.adapter = adap

        // TODO Observer on all your Data!
        mainViewModel.allDataFinal.observe (this, {datalist ->
            adap.setmyDataList(datalist)
            datalistall = datalist
        })

        cb1.setOnClickListener {
            val desc = ctf1.text.toString()
            val id = db.collection("letChatDB").id
            val myData = MyData(desc, email!!,id)
            mainViewModel.addData(myData)
            mainViewModel.fetchData().observe (this, {datalist ->
                adap.setmyDataList(datalist)
                datalistall = datalist
            })
        }
    }
}