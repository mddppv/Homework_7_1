package com.example.homework_7_1.domain.repository

import com.example.homework_7_1.data.local.dao.AppDao
import com.example.homework_7_1.data.remote.model.DoorModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface DoorRepository {
    suspend fun getDoors(): List<DoorModel>
    suspend fun setDoors(doorModel: DoorModel)
}

class DoorRepositoryFuncs(private val appDao: AppDao) : DoorRepository {
    override suspend fun getDoors(): List<DoorModel> {
        return withContext(Dispatchers.IO) {
            appDao.getDoors()
        }
    }

    override suspend fun setDoors(doorModel: DoorModel) {
        withContext(Dispatchers.IO) {
            appDao.setDoors(doorModel)
        }
    }
}