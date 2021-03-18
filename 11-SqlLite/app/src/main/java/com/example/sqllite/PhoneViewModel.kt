package com.example.sqllite

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class PhoneViewModel(application: Application) : AndroidViewModel(application) {

    private val repo: PhoneRepo
    val allPhones: LiveData<List<Phone>>

    init {
        repo = PhoneRepo(PhoneDB.getDatabase(application).phonedao())
        allPhones = repo.allPhones
    }


    fun insert(phone: Phone) = repo.insert(phone)
    fun delete(phone: Phone) = repo.delete(phone)
    fun deleteAll() = repo.deleteAll()

}