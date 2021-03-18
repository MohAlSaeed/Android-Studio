package com.example.sqllite

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface Phonedao {

    @Query("SELECT * FROM Phone" )
    fun getPhones(): LiveData<List<Phone>>

    @Insert
    fun insert(nphone: Phone)

    @Delete
    fun delete(rphone: Phone)

    @Query("DELETE FROM Phone")
    fun deleteALL()

}