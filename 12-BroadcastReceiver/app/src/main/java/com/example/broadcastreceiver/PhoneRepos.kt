package com.example.broadcastreceiver


// we had :_   Room --> Repository --> View Model< liveData > --> Activity
class PhoneRepos(private val phoneDao: PhoneDao) {
    val allPhones = phoneDao.getPhones()

    suspend fun insert(phone : Phone) = phoneDao.insert(phone)
    suspend fun delete(phone: Phone) = phoneDao.delete(phone)
    suspend fun update(phone: Phone) = phoneDao.update(phone)
    suspend fun deleteAll() = phoneDao.deleteAll()

}