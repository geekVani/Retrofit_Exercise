package com.example.retrofitexercise.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.retrofitexercise.model.UserRepository
import com.example.retrofitexercise.model.roomDB.UserDatabase
import com.example.retrofitexercise.model.roomDB.UserEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application):
    AndroidViewModel(application) {

    // on below line we are creating a variable
    // for our all notes list and repository
    val allUsers : LiveData<List<UserEntity>>
    val repository : UserRepository

    // on below line we are initializing
    // our dao, repository and all notes
    init {
        val dao = UserDatabase.getDatabase(application).getUserDao()
        repository = UserRepository(dao)
        allUsers = repository.allUsers
    }
    // on below line we are creating a new method for adding a new note to our database
    // we are calling a method from our repository to add a new note.
    fun addUser(userEntity: UserEntity) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(userEntity)
    }
}