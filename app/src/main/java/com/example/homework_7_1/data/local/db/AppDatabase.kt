package com.example.homework_7_1.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.homework_7_1.data.local.dao.AppDao
import com.example.homework_7_1.data.remote.converter.CameraDataConverter
import com.example.homework_7_1.data.remote.converter.DoorDataConverter
import com.example.homework_7_1.data.remote.model.CameraModel
import com.example.homework_7_1.data.remote.model.DoorModel

@Database(entities = [CameraModel::class, DoorModel::class], version = 1)
@TypeConverters(CameraDataConverter::class, DoorDataConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun appDao(): AppDao
}