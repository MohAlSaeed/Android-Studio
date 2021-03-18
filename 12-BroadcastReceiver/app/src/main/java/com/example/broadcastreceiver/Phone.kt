package com.example.broadcastreceiver

import androidx.room.Entity
import androidx.room.PrimaryKey

// after you add the @Entity you have to
// //to add
// def room_version = "2.2.5"
// //and to add
// implementation "androidx.room:room-runtime:$room_version"
// into gradle Module

@Entity
data class Phone(
    @PrimaryKey(autoGenerate = true) var id: Long = 0,
    var name: String,
    var price: Int,
    var quantity: Int,
    var available: Boolean
    )
