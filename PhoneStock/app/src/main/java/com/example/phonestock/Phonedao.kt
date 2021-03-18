package com.example.phonestock

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface Phonedao {

    @Query("SELECT * FROM Phone")
    fun getPhones(): LiveData<List<Phone>>

    @Insert
    suspend fun insert(nphone: Phone)

    @Delete
    suspend fun delete(rphone: Phone)

    @Query("DELETE FROM Phone")
    suspend fun deleteALL()

    @Update
    suspend fun update(uphone: Phone)

//    @Query("UPDATE Phone SET available = :end_address WHERE id = :tid")
//    fun updateTour(tid: Long, end_address: String?): Int

    // Method 2:

//    // Method 2:
//    @Query("UPDATE Phone SET available = true WHERE id = :id")
//    fun update(Boolean Long, end_address: String?): Int
}