package com.example.homework_7_1.app

import android.app.Application
import androidx.room.Room
import com.example.homework_7_1.data.local.db.AppDatabase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
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