package com.example.sqllite

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import java.security.AccessControlContext

@Database(entities = [Phone::class], version = 1)
abstract class PhoneDB: RoomDatabase(){

    abstract fun phonedao(): Phonedao

    companion object{
        private var myinstance : PhoneDB? = null

        fun getDatabase(context: Context): PhoneDB{
            if (myinstance != null)
                return myinstance as PhoneDB
            myinstance = Room.databaseBuilder(
                context.applicationContext,
                PhoneDB::class.java,
                "PhoneDatabases"
            ).allowMainThreadQueries().build()
            return myinstance as PhoneDB
        }
    }
}