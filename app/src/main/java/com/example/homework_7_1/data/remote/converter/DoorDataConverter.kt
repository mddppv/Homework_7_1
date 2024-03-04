package com.example.homework_7_1.data.remote.converter

import androidx.room.TypeConverter
import com.example.homework_7_1.data.remote.model.DoorModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DoorDataConverter {

    private val gson = Gson()

    @TypeConverter
    fun fromString(value: String): List<DoorModel.Data> {
        val listType = object : TypeToken<List<DoorModel.Data>>() {}.type
        return gson.fromJson(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<DoorModel.Data>): String {
        return gson.toJson(list)
    }
}