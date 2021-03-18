package com.mt.letschatmt

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
//you can pass here the db instance
class MyRepo(private val db : FirebaseFirestore) {
//    val db = FirebaseFirestore.getInstance()

    fun getData(): LiveData<MutableList<MyData>> {
        val mutableData = MutableLiveData<MutableList<MyData>>()
        db.collection("letChatDB").get().addOnSuccessListener {result ->
            val listData = mutableListOf<MyData>()
            for (doc in result){
                val desc = doc.getString("desc")
                val email = doc.getString("email")
                val id = doc.getString("id")
                val myData = MyData(desc!!, email!!, id!!)
                listData.add(myData)
            }
            mutableData.value = listData
        }
        return mutableData
    }

    // add items
    fun addItemData(myData: MyData) {
        var tmpid : String =""
        db.collection("letChatDB").add(myData)
            .addOnSuccessListener {
            tmpid = it.id
            myData.id = tmpid
//            Log.e("id", "this is the ID:  ${tmpid}")
            db.collection("letChatDB").document(tmpid).set(myData)
        }

    }

    // delete one data
    fun deleteItemData(myData: MyData) {
        db.collection("letChatDB").document(myData.id).delete()
    }


}