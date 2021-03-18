package com.example.phonestock

class PhoneRepo(private val phonedao : Phonedao) {

    val allPhones = phonedao.getPhones()

    suspend fun insert(phone: Phone) = phonedao.insert(phone)

    suspend fun delete(phone: Phone) = phonedao.delete(phone)

    suspend fun update(phone: Phone) = phonedao.update(phone)

    suspend fun deleteAll() = phonedao.deleteALL()

}