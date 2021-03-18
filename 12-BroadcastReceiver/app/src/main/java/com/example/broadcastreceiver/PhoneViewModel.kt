package com.example.broadcastreceiver

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
// this class should take Application and return AndroidViewModel
class PhoneViewModel(application: Application) : AndroidViewModel(application) {

    private val repo : PhoneRepos
    val allPhones: LiveData<List<Phone>>

    init {
        repo = PhoneRepos(PhoneDB2.getDatabase(application).phDao())
        allPhones = repo.allPhones
    }

    suspend fun insert(phone : Phone) = repo.insert(phone)
    suspend fun delete(phone : Phone) = repo.delete(phone)
    suspend fun deleteAll() = repo.deleteAll()
    suspend fun update(phone : Phone) = repo.update(phone)

}