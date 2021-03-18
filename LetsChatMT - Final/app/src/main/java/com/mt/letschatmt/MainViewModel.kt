package com.mt.letschatmt

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore

class MainViewModel(application: Application) : AndroidViewModel(application){
    private val repo : MyRepo
    val allDataFinal : LiveData<MutableList<MyData>>
    init {
        repo = MyRepo(FirebaseFirestore.getInstance())
        allDataFinal = repo.getData()
    }

    fun fetchData():LiveData<MutableList<MyData>>{
        val mymutableData = MutableLiveData<MutableList<MyData>>()
        repo.getData().observeForever { result ->
            mymutableData.value = result
        }
        return mymutableData
    }

    // add items
    fun addData(myData: MyData) {
        repo.addItemData(myData)
    }

    // delete items
    fun deleteData(myData: MyData) {
        repo.deleteItemData(myData)

    }


}


