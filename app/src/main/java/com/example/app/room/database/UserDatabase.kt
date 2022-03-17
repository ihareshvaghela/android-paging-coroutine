package com.example.app.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.app.room.dao.UserDao
import com.example.app.room.entity.User

@Database(entities = arrayOf(User::class), version = 1)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao():UserDao
}