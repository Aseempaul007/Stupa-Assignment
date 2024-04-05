package com.example.stupa.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "user")
data class User(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "user_name")
    val userName: String,

    @ColumnInfo(name = "user_email")
    val userEmail: String,

    @ColumnInfo(name = "user_password")
    val userPassword: String,

    @ColumnInfo(name = "user_country")
    val userCountry: String?,

    @ColumnInfo(name = "user_phone_number")
    val userPhoneNumber: String?

) : Serializable
