package com.example.homework_7_1.data.remote.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "doors")
data class DoorModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val success: Boolean,
    val data: List<Data>
) {
    data class Data(
        var favorites: Boolean,
        val id: Int,
        val name: String,
        val room: String,
        val snapshot: String,
        var visible: Boolean = false
    )
}