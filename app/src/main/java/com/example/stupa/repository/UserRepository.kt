package com.example.stupa.repository

import com.example.stupa.data.local.dao.UserDao
import com.example.stupa.data.local.entity.User
import javax.inject.Inject

class UserRepository @Inject constructor(private val dao: UserDao) {

    suspend fun addStudent(user: User) {
        dao.addUser(user)
    }

    suspend fun showStudents(): List<User> {
        val list = dao.listOfUsers()
        return list
    }
}