package com.example.rvfb

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QueryDocumentSnapshot

class PhonesRepo {

    fun getPhoneData(): LiveData<MutableList<Phones>>{
        val mutableData = MutableLiveData<MutableList<Phones>>()
        FirebaseFirestore.getInstance().collection("Phones").get().addOnSuccessListener {result ->
            val listData : MutableList<Phones> = mutableListOf<Phones>()
            for(document in result){
                val name = document.getString("name")
                val price = document.getString("price")
                val quantity = document.getString("quantity")
                val available = document.getString("available")
                val ph = Phones(name!!, price!!, quantity!!, available!!)
                listData.add(ph)
            }
            mutableData.value = listData
        }
        return mutableData
        }

}