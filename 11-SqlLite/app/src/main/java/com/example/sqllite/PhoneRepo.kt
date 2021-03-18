package com.example.sqllite

class PhoneRepo(private val phonedao : Phonedao) {

    val allPhones = phonedao.getPhones()

    fun insert(phone: Phone) = phonedao.insert(phone)

    fun delete(phone: Phone) = phonedao.delete(phone)

    fun deleteAll() = phonedao.deleteALL()

}