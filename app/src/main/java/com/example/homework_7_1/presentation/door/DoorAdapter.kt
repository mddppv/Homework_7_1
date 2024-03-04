package com.example.homework_7_1.presentation.door

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.homework_7_1.data.remote.model.DoorModel
import com.example.homework_7_1.databinding.ItemDoorScreenBinding
import com.example.homework_7_1.util.ItemClickListener
import com.example.homework_7_1.util.gone
import com.example.homework_7_1.util.visible

class DoorAdapter(
    private var dataList: List<DoorModel.Data>, private val clickListener: ItemClickListener
) : RecyclerView.Adapter<DoorAdapter.ViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newData: List<DoorModel.Data>) {
        dataList = newData
        notifyDataSetChanged()
    }

    fun toggleVisibility(position: Int) {
        dataList[position].visible = !dataList[position].visible
        notifyItemChanged(position)
    }

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
        fun bind(door: DoorModel.Data, clickListener: ItemClickListener) {
            with(binding) {
                if (door.visible) ivImage.visible() else ivImage.gone()
                Glide.with(itemView).load(door.snapshot).into(ivImage)
                tvTitle.text = door.name
                tvStatus.text = door.room

                itemView.setOnClickListener {
                    clickListener.onItemClicked(adapterPosition)
                }
            }
        }
    }
}