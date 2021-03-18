package com.example.phonestock

import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PhoneRepo(private val phonedao : Phonedao) {

    val allPhones = phonedao.getPhones()

    suspend fun insert(phone: Phone) = phonedao.insert(phone)

    suspend fun delete(phone: Phone) = phonedao.delete(phone)

    suspend fun update(phone: Phone) = phonedao.update(phone)

    suspend fun deleteAll() = phonedao.deleteALL()


    init {
        ref.addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previous: String?) {
                CoroutineScope(Dispatchers.IO).launch {
                    allPhones.value.add(snapshot.value as Phone)
                }
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                TODO("Not yet implemented")
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
                TODO("Not yet implemented")
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
                TODO("Not yet implemented")
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }

}