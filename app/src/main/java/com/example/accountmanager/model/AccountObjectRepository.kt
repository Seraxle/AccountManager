package com.example.accountmanager.model

import androidx.lifecycle.LiveData

class AccountObjectRepository(private val accountObjectDao: AccountObjectDAO) {

    val readAllData: LiveData<List<AccountObject>> = accountObjectDao.readAllData()

    suspend fun addAccount(acct: AccountObject) {
        accountObjectDao.addAccount(acct)
    }

    suspend fun updateAccount(acct: AccountObject) {
        accountObjectDao.updateAccount(acct)
    }

    suspend fun deleteAccount(acct: AccountObject) {
        accountObjectDao.deleteAccount(acct)
    }
}