package com.example.stupa.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.stupa.data.local.entity.User

@Dao
interface UserDao {

    // for adding user
    @Insert
    suspend fun addUser(user: User)

    // for getting all users
    @Query("SELECT * FROM user")
    suspend fun listOfUsers(): List<User>

}