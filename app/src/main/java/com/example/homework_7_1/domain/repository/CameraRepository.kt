package com.example.homework_7_1.domain.repository

import com.example.homework_7_1.data.local.dao.AppDao
import com.example.homework_7_1.data.remote.model.CameraModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface CameraRepository {
    suspend fun getCameras(): List<CameraModel>
    suspend fun setCameras(cameraModel: CameraModel)
}

class CamRepositoryFuncs(private val appDao: AppDao) : CameraRepository {
    override suspend fun getCameras(): List<CameraModel> {
        return withContext(Dispatchers.IO) {
            appDao.getCameras()
        }
    }

    override suspend fun setCameras(cameraModel: CameraModel) {
        withContext(Dispatchers.IO) {
            appDao.setCameras(cameraModel)
        }
    }
}