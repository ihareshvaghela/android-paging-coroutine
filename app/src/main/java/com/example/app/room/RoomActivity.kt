package com.example.app.room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.room.Room
import com.example.app.R
import com.example.app.databinding.ActivityRoomBinding
import com.example.app.room.database.UserDatabase
import com.example.app.room.entity.User

class RoomActivity : AppCompatActivity() {

    lateinit var binding: ActivityRoomBinding

    private val DATABASE_NAME: String = "USER_DATABASE"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRoomBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRoomDatabase()
    }

    private fun setupRoomDatabase() {
        val db = Room.databaseBuilder(applicationContext, UserDatabase::class.java, DATABASE_NAME)
            .allowMainThreadQueries()
            .build()

        val userDao = db.userDao()

        val users: List<User> = userDao.getAll()
        Log.d("ROOM_DB", if (users.isEmpty()) "{no data available }" else users[0].firstName)

        val user = User(0, "Hello", "World")
        userDao.insertAll(user)
    }
}