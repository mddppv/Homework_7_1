package com.example.homework_7_1.presentation.cam

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.homework_7_1.R
import com.example.homework_7_1.data.remote.model.CameraModel
import com.example.homework_7_1.databinding.ItemCamScreenBinding

class CamAdapter(private var dataList: List<CameraModel.Data.Camera>) :
    RecyclerView.Adapter<CamAdapter.ViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newData: List<CameraModel.Data.Camera>) {
        dataList = newData
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCamScreenBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount() = dataList.size

    inner class ViewHolder(private val binding: ItemCamScreenBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(camera: CameraModel.Data.Camera) {
            with(binding) {
                Glide.with(itemView).load(R.drawable.room).into(ivCam)
                tvCam.text = camera.name
                tvRoom.text = camera.room
                setFavIcon(camera.favorites)

                ivStar.setOnClickListener {
                    camera.favorites = !camera.favorites
                    setFavIcon(camera.favorites)
                }
            }
        }

        private fun setFavIcon(favorite: Boolean) {
            if (favorite) {
                binding.ivStar.setImageResource(R.drawable.star)
            } else {
                binding.ivStar.setImageResource(R.drawable.star_border)
            }
        }
    }
}