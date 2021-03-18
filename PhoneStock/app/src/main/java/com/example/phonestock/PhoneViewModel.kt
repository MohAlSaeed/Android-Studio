package com.example.phonestock

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


    suspend fun insert(phone: Phone) = repo.insert(phone)
    suspend fun delete(phone: Phone) = repo.delete(phone)
    suspend fun update(phone: Phone) = repo.update(phone)
    suspend fun deleteAll() = repo.deleteAll()

}