package com.example.retrofitexercise.model

import androidx.lifecycle.LiveData
import com.example.retrofitexercise.model.roomDB.UserDao
import com.example.retrofitexercise.model.roomDB.UserEntity

/**
 * A repository mediates between data sources (such
 * as persistent models, web services, and caches)
 * and the rest of the app. The diagram below shows
 * how app components such as activities that use
 * “LiveData” might interact with data sources
 * through a repository.
 * */

class UserRepository(private val userDao: UserDao) {

    // on below line we are creating a variable for our list
    // and we are getting all the users from our DAO class.
    val allUsers: LiveData<List<UserEntity>> = userDao.getallUsers()


    // on below line we are creating an insert method
    // for adding the user to our database.
    suspend fun insert(userEntity: UserEntity){
        userDao.insert(userEntity)
    }

//    suspend fun getUserEmail(userEmail: String): UserEntity?{
//        return userDao.getuserEmail(userEmail)
//    }
}