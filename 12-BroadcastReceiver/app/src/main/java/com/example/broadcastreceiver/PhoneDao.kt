package com.example.broadcastreceiver

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface PhoneDao {
    @Query("select * from Phone")
    fun getPhones() : LiveData<List<Phone>>

    @Insert
    suspend fun insert(phone : Phone)

    @Delete
    suspend fun delete(phone : Phone)

    @Query("DELETE from Phone")
    suspend fun deleteAll()

    @Update
    suspend fun update(phone: Phone)


}