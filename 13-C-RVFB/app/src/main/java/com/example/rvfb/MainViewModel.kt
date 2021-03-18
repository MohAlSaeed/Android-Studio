package com.example.rvfb

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {

    private val repo = PhonesRepo()
    fun fetchPhoneData(): LiveData<MutableList<Phones>>{
        val mutableData = MutableLiveData<MutableList<Phones>>()
        repo.getPhoneData().observeForever {phoneList ->
            mutableData.value = phoneList

        }
        return mutableData
    }

}