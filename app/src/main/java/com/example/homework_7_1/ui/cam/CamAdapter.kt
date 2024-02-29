package com.example.homework_7_1.ui.cam

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.homework_7_1.databinding.ItemCamScreenBinding
import com.example.homework_7_1.model.CamModel

class CamAdapter(private val dataList: List<CamModel>) :
    RecyclerView.Adapter<CamAdapter.ViewHolder>() {

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

        fun bind(camModel: CamModel) {
            binding.ivCam.setImageResource(camModel.camImage)
            binding.tvCam.text = camModel.camTitle
        }
    }
}