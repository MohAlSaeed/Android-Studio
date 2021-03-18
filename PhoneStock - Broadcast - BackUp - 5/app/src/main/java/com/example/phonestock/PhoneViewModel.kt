package com.example.phonestock

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.google.firebase.DataCollectionDefaultChange
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PhoneViewModel(application: Application,val ref : DatabaseReference,val list : ArrayList<Phone>) : AndroidViewModel(application) {

    private val repo: PhoneRepo
    val allPhones: LiveData<List<Phone>>

    init {
        repo = PhoneRepo(PhoneDB.getDatabase(application).phonedao())
        allPMWShones = repo.allPhones
    }


    suspend fun insert(phone: Phone) = repo.insert(phone)
    suspend fun delete(phone: Phone) = repo.delete(phone)
    suspend fun update(phone: Phone) = repo.update(phone)
    suspend fun deleteAll() = repo.deleteAll()



    

}