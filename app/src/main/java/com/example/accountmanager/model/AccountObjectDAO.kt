package com.example.accountmanager.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface AccountObjectDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addAccount(acct: AccountObject)

    @Update
    suspend fun updateAccount(acct: AccountObject)

    @Delete
    suspend fun deleteAccount(acct: AccountObject)

    // account_table
    @Query("SELECT * FROM account_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<AccountObject>>
}