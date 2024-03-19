package com.example.homework_7_1.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.homework_7_1.data.remote.model.CameraModel
import com.example.homework_7_1.data.remote.model.DoorModel

@Dao
interface AppDao {

    @Insert
    suspend fun setCameras(cameraModel: CameraModel)

    @Query("SELECT * FROM cameras")
    suspend fun getCameras(): List<CameraModel>

    @Insert
    suspend fun setDoors(doorModel: DoorModel)

    @Query("SELECT * FROM doors")
    suspend fun getDoors(): List<DoorModel>

    @Delete
    suspend fun deleteDoor(doorModel: DoorModel)

}