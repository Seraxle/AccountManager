package com.example.accountmanager.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "account_table")
data class AccountObject(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val platform: String,
    val username: String,
    val password: String
): Parcelable