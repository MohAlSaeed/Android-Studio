package com.example.firebaselogin

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class PhoneViewModel(application: Application) : AndroidViewModel(application) {
    private val repo : FirebaseRepo
//    val allPhones: LiveData<List<Phone>>

    init {
        repo = FirebaseRepo()
//        allPhones = repo.getallphones().result
    }
}