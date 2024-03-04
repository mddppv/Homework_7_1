package com.example.homework_7_1.data.remote.converter

import androidx.room.TypeConverter
import com.example.homework_7_1.data.remote.model.CameraModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CameraDataConverter {

    private val gson = Gson()

    @TypeConverter
    fun fromString(value: String): CameraModel.Data {
        val dataType = object : TypeToken<CameraModel.Data>() {}.type
        return gson.fromJson(value, dataType)
    }

    @TypeConverter
    fun fromData(data: CameraModel.Data): String {
        return gson.toJson(data)
    }
}