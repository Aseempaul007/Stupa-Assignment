package com.example.stupa.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stupa.data.local.entity.User
import com.example.stupa.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewmodel @Inject constructor(private var repository: UserRepository) : ViewModel() {

    suspend fun addStudent(user: User) {
        repository.addStudent(user)
    }

    suspend fun showStudents(): List<User> {
        val list = repository.showStudents()
        return list
    }

}