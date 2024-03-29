package com.example.homework_7_1.presentation.door

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.homework_7_1.R
import com.example.homework_7_1.app.App.Companion.database
import com.example.homework_7_1.data.local.dao.AppDao
import com.example.homework_7_1.data.remote.model.DoorModel
import com.example.homework_7_1.data.remote.retrofit.RetrofitClient
import com.example.homework_7_1.data.remote.retrofit.RetrofitService
import com.example.homework_7_1.databinding.FragmentDoorScreenBinding
import com.example.homework_7_1.domain.repository.DoorRepository
import com.example.homework_7_1.domain.repository.DoorRepositoryFuncs
import com.example.homework_7_1.util.ItemClickListener
import com.example.homework_7_1.util.gone
import com.example.homework_7_1.util.showToast
import com.example.homework_7_1.util.visible
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DoorScreenFragment : Fragment(), ItemClickListener {

    private val binding: FragmentDoorScreenBinding by lazy {
        FragmentDoorScreenBinding.inflate(layoutInflater)
    }
    private val adapter: DoorAdapter by lazy {
        DoorAdapter(emptyList(), this)
    }
    private val retrofitService: RetrofitService by lazy {
        RetrofitClient.retrofitClient.create(RetrofitService::class.java)
    }
    private val appDao: AppDao by lazy {
        database.appDao()
    }
    private lateinit var repository: DoorRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
        initRepository()
        showLoading()
        dataFromServer()
    }

    private fun initAdapter() {
        binding.rvDoor.adapter = adapter
        adapter.setSwipeListener(this)
        adapter.swipeManager(binding.rvDoor)
    }

    private fun initRepository() {
        repository = DoorRepositoryFuncs(appDao)
    }

    private fun dataFromServer() {
        lifecycleScope.launch {
            val doors = repository.getDoors()
            if (doors.isEmpty()) {

                retrofitService.getDoors().enqueue(object : Callback<DoorModel> {
                    override fun onResponse(call: Call<DoorModel>, response: Response<DoorModel>) {
                        hideLoading()
                        if (response.isSuccessful) {
                            val doorModel = response.body()
                            if (doorModel != null) {
                                lifecycleScope.launch(Dispatchers.IO) {
                                    appDao.setDoors(doorModel)
                                }
                                adapter.updateData(doorModel.data)
                                requireContext().showToast("Door data loaded from server")
                            }
                        }
                    }

                    override fun onFailure(call: Call<DoorModel>, t: Throwable) {
                        hideLoading()
                        Log.e("wow", "onFailure: DoorScreenFragment")
                        requireContext().showToast("Error! Door data not loaded")
                    }
                })
            } else {
                hideLoading()
                adapter.updateData(doors[0].data)
                requireContext().showToast("Door data loaded from database")
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

    override fun onItemClicked(position: Int) {
        adapter.toggleVisibility(position)
    }

    override fun onEditClicked(position: Int) {
        findNavController().navigate(R.id.doorEditFragment)
    }

    override fun onDeleteClicked(position: Int) {
        lifecycleScope.launch {
            val doorData = adapter.getItemAt(position)
            val door = DoorModel(success = true, data = listOf(doorData))
            withContext(Dispatchers.IO) {
                appDao.deleteDoor(door)
            }
            adapter.removeItem(position)
            requireContext().showToast("Door deleted")
        }
    }

    override fun onFavoriteClicked(position: Int) {
        lifecycleScope.launch {
            val doorData = adapter.getItemAt(position)
            doorData.favorites = !doorData.favorites
            adapter.notifyItemChanged(position)
        }
    }
}