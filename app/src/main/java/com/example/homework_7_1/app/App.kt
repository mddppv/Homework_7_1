package com.example.homework_7_1.app

import android.app.Application
import androidx.room.Room
import com.example.homework_7_1.data.local.db.AppDatabase

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "app-database"
        ).build()
    }

    companion object {
        lateinit var database: AppDatabase
    }
}