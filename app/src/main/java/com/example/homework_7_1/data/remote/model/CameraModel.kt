package com.example.homework_7_1.data.remote.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cameras")
data class CameraModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val success: Boolean,
    val data: Data
) {
    data class Data(
        val cameras: List<Camera>,
        val room: List<String>
    ) {
        data class Camera(
            val favorites: Boolean,
            val id: Int,
            val name: String,
            val rec: Boolean,
            val room: String?,
            val snapshot: String
        )
    }
}