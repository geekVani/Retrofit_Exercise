package com.example.retrofitexercise.model.roomDB

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {

    @Insert
    suspend fun insert(userEntity: UserEntity)

    @Query("SELECT * FROM userTable WHERE userEmail LIKE :userEmail")
    suspend fun getUserByEmail(userEmail: String): UserEntity?

    @Query("SELECT * FROM userTable ORDER BY userId ASC")
    fun getallUsers(): LiveData<List<UserEntity>>

    /*
    * This command compares the string received as the
    * argument with the attribute UserName and returns
    * all the records that belong to that particular username.
    * If the username doesn't exist, it returns NULL
//    * */

}