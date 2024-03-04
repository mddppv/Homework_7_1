package com.example.homework_7_1.data.remote.retrofit

import com.example.homework_7_1.data.remote.model.CameraModel
import com.example.homework_7_1.data.remote.model.DoorModel
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitService {

    @GET("cameras/")
    fun getCameras(): Call<CameraModel>

    @GET("doors/")
    fun getDoors(): Call<DoorModel>

}