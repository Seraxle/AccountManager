package com.example.accountmanager.viewmodel

import androidx.lifecycle.ViewModel
import com.example.accountmanager.model.AccountObject

class RecyclerViewModel: ViewModel() {
    private var chosenAccount: AccountObject = AccountObject(0, "", "", "")
    private var list: ArrayList<AccountObject> = ArrayList()

    fun getChosenAccount(): AccountObject {
        return this.chosenAccount
    }

    fun setChosenAccount(account: AccountObject) {
        this.chosenAccount = account
    }

    fun getList(): ArrayList<AccountObject> {
        return this.list
    }

    fun setList(recyclerList: ArrayList<AccountObject>) {
        this.list = recyclerList
    }
}