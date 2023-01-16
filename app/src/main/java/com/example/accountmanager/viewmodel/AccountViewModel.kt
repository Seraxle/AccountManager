package com.example.accountmanager.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.accountmanager.model.AccountDatabase
import com.example.accountmanager.model.AccountObject
import com.example.accountmanager.model.AccountObjectRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AccountViewModel(application: Application): AndroidViewModel(application) {

    private val readAllData: LiveData<List<AccountObject>>
    private val repository: AccountObjectRepository

    init {
        val accountObjectDao = AccountDatabase.getDatabase(application).accountObjectDao()
        repository = AccountObjectRepository(accountObjectDao)
        readAllData = repository.readAllData
    }

    fun addAccount(acct: AccountObject) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addAccount(acct)
        }
    }

    fun readAllData(): LiveData<List<AccountObject>> {
        return this.readAllData
    }

    fun updateUser(acct: AccountObject) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateAccount(acct)
        }
    }

    fun deleteAccount(acct: AccountObject) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAccount(acct)
        }
    }
}