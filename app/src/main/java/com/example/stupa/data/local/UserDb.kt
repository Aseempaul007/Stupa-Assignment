package com.example.stupa.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.stupa.data.local.dao.UserDao
import com.example.stupa.data.local.entity.User

@Database(
    entities = [User::class],
    version = 1
)
abstract class UserDb : RoomDatabase() {
    abstract fun dao(): UserDao
}