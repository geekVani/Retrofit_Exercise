package com.example.retrofitexercise.model.roomDB

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "userTable")
data class UserEntity(
    var userEmail: String,
    var userPhone: String,
    var userName: String,
    var userPwd: String,
    var confirmPwd: String
) {
    @PrimaryKey(autoGenerate = true)
    var userId: Int = 0
}
