package com.example.joysticks.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.joysticks.model.UserModel

@Database(entities = [UserModel::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
     abstract fun getUserDao() :UserDao
}