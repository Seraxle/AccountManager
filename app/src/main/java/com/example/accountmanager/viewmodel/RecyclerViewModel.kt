package com.example.accountmanager.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.accountmanager.model.AccountObject

class RecyclerViewModel: ViewModel() {
    private var chosenAccount: MutableLiveData<AccountObject> = MutableLiveData()
    private var list: MutableLiveData<List<AccountObject>> = MutableLiveData()

    fun getChosenAccount(): AccountObject? {
        return this.chosenAccount.value
    }

    fun setChosenAccount(account: AccountObject) {
        this.chosenAccount.value = account
    }

    fun getList(): List<AccountObject>? {
        return this.list.value
    }

    fun setList(recyclerList: List<AccountObject>) {
        this.list.value = recyclerList
    }
}