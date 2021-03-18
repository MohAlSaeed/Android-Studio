package com.example.phonestock

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Phone(
    @PrimaryKey(autoGenerate = true) var id: Long = 0,
    var name: String,
    var price: Int,
    var quantity: Int,
    var available: Boolean) {
}