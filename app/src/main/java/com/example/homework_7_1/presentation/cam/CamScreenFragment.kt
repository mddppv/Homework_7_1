package com.example.homework_7_1.presentation.cam

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.homework_7_1.data.local.dao.AppDao
import com.example.homework_7_1.data.remote.model.CameraModel
import com.example.homework_7_1.data.remote.retrofit.RetrofitClient
import com.example.homework_7_1.data.remote.retrofit.RetrofitService
import com.example.homework_7_1.databinding.FragmentCamScreenBinding
import com.example.homework_7_1.util.App.Companion.database
import com.example.homework_7_1.util.gone
import com.example.homework_7_1.util.showToast
import com.example.homework_7_1.util.visible
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CamScreenFragment : Fragment() {

    private val binding: FragmentCamScreenBinding by lazy {
        FragmentCamScreenBinding.inflate(layoutInflater)
    }
    private val adapter: CamAdapter by lazy {
        CamAdapter(emptyList())
    }
    private val retrofitService: RetrofitService by lazy {
        RetrofitClient.retrofitClient.create(RetrofitService::class.java)
    }
    private val appDao: AppDao by lazy {
        database.appDao()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvCam.adapter = adapter

        showLoading()
        dataFromServer()
    }

    private fun dataFromServer() {
        lifecycleScope.launch {
            val cameras = withContext(Dispatchers.IO) {
                appDao.getCameras()
            }
            if (cameras.isEmpty()) {
                delay(5000)

                retrofitService.getCameras().enqueue(object : Callback<CameraModel> {
                    override fun onResponse(
                        call: Call<CameraModel>, response: Response<CameraModel>
                    ) {
                        hideLoading()
                        if (response.isSuccessful) {
                            val cameraModel = response.body()
                            if (cameraModel != null) {
                                lifecycleScope.launch(Dispatchers.IO) {
                                    appDao.setCameras(cameraModel)
                                }
                                adapter.updateData(cameraModel.data.cameras)
                                requireContext().showToast("Camera data loaded from server")
                            }
                        }
                    }

                    override fun onFailure(call: Call<CameraModel>, t: Throwable) {
                        hideLoading()
                        Log.e("wow", "onFailure: CamScreenFragment")
                        requireContext().showToast("Error! Camera data not loaded")
                    }
                })
            } else {
                hideLoading()
                adapter.updateData(cameras[0].data.cameras)
                requireContext().showToast("Camera data loaded from database")
            }
        }
    }

    private fun showLoading() {
        with(binding) {
            progressBar.visible()
            ivLoading.visible()
        }
    }

    private fun hideLoading() {
        with(binding) {
            progressBar.gone()
            ivLoading.gone()
        }
    }
}