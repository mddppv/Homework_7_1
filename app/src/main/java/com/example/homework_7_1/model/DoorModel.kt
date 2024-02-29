package com.example.homework_7_1.model

data class DoorModel(
    val doorImage: Int,
    val doorTitle: String,
    val doorStatus: String,
    var isVisible: Boolean = false
)