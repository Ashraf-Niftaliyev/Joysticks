package com.example.joysticks.roomdb

import android.content.Context
import androidx.room.Room
import com.example.joysticks.model.UserModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AppRepo(var context: Context) {

    var database = Room.databaseBuilder(context,AppDatabase::class.java,"app_database").build()

    private var getDao = database.getUserDao()

    suspend fun getAllUsers() = withContext(Dispatchers.IO){
        getDao.getAllUsers()
    }

    suspend fun getAllUsersEmail(e:String,p:String) = withContext(Dispatchers.IO){
        getDao.getAllUsersEmail(e,p)
    }

    suspend fun insertUser(user: UserModel) = withContext(Dispatchers.IO){
        getDao.insertUser(user)
    }

    suspend fun deleteUser(user: UserModel) = withContext(Dispatchers.IO){
        getDao.deleteUser(user)
    }
}