package com.example.broadcastreceiver

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Phone::class], version = 1)
abstract class PhoneDB2: RoomDatabase(){

    abstract fun phDao(): PhoneDao

    companion object{
        private var myinstance : PhoneDB2? = null

        fun getDatabase(context: Context): PhoneDB2{
            if (myinstance != null)
                return myinstance as PhoneDB2
            myinstance = Room.databaseBuilder(
                context.applicationContext,
                PhoneDB2::class.java,
                "PhoneDatabases2020"
            ).build()
            return myinstance as PhoneDB2
        }
    }
}