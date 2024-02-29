package com.example.homework_7_1.ui.door

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.homework_7_1.databinding.ItemDoorScreenBinding
import com.example.homework_7_1.model.DoorModel
import com.example.homework_7_1.util.ItemClickListener
import com.example.homework_7_1.util.gone
import com.example.homework_7_1.util.visible

class DoorAdapter(
    private val dataList: List<DoorModel>,
    private val clickListener: ItemClickListener
) : RecyclerView.Adapter<DoorAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemDoorScreenBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataList[position], clickListener)
    }

    override fun getItemCount() = dataList.size

    inner class ViewHolder(private val binding: ItemDoorScreenBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(doorModel: DoorModel, clickListener: ItemClickListener) {
            with(binding) {
                ivDoorImage.setImageResource(doorModel.doorImage)
                tvDoorTitle.text = doorModel.doorTitle
                tvDoorStatus.text = doorModel.doorStatus

                if (doorModel.isVisible) {
                    ivDoorImage.visible()
                } else {
                    ivDoorImage.gone()
                }

                itemView.setOnClickListener {
                    clickListener.onItemClicked(adapterPosition)
                }
            }
        }
    }
}