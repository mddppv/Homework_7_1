package com.example.homework_7_1.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.homework_7_1.data.remote.model.CameraModel
import com.example.homework_7_1.data.remote.model.DoorModel

@Dao
interface AppDao {

    @Insert
    fun setCameras(cameraModel: CameraModel)

    @Query("SELECT * FROM cameras")
    fun getCameras(): List<CameraModel>

    @Insert
    fun setDoors(doorModel: DoorModel)

    @Query("SELECT * FROM doors")
    fun getDoors(): List<DoorModel>

}