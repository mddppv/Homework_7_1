package com.example.homework_7_1.presentation.door

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.homework_7_1.R
import com.example.homework_7_1.data.remote.model.DoorModel
import com.example.homework_7_1.databinding.ItemDoorScreenBinding
import com.example.homework_7_1.util.ItemClickListener
import com.example.homework_7_1.util.gone
import com.example.homework_7_1.util.visible

class DoorAdapter(
    private var dataList: List<DoorModel.Data>, private val clickListener: ItemClickListener
) : RecyclerView.Adapter<DoorAdapter.ViewHolder>() {

    private lateinit var swipeListener: ItemClickListener
    private var swipePosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemDoorScreenBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataList[position], clickListener)
        holder.actionsLogic(position)
    }

    override fun getItemCount() = dataList.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newData: List<DoorModel.Data>) {
        dataList = newData
        notifyDataSetChanged()
    }

    fun setSwipeListener(listener: ItemClickListener) {
        swipeListener = listener
    }

    fun getItemAt(position: Int): DoorModel.Data {
        return dataList[position]
    }

    fun removeItem(position: Int) {
        dataList = dataList.toMutableList().also { it.removeAt(position) }
        notifyItemRemoved(position)
    }

    fun toggleVisibility(position: Int) {
        dataList[position].visible = !dataList[position].visible
        notifyItemChanged(position)
    }

    fun swipeManager(recyclerView: RecyclerView) {
        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
            0, ItemTouchHelper.LEFT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                when (direction) {
                    ItemTouchHelper.LEFT -> {
                        swipePosition = position
                        toggleVisibility(position)
                    }
                }
            }

            override fun getSwipeDirs(
                recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder
            ): Int {
                return if (swipePosition == -1) {
                    super.getSwipeDirs(recyclerView, viewHolder)
                } else {
                    0
                }
            }
        }

        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    inner class ViewHolder(private val binding: ItemDoorScreenBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(door: DoorModel.Data, clickListener: ItemClickListener) {
            with(binding) {
                if (door.visible) {
                    ivImage.visible()
                    ivStar.visible()
                } else {
                    ivImage.gone()
                    ivStar.gone()
                    swipePosition = -1
                }
                Glide.with(itemView).load(R.drawable.room).into(ivImage)
                tvTitle.text = door.name
                tvStatus.text = door.room
                setFavIcon(door.favorites)

                itemView.setOnClickListener {
                    clickListener.onItemClicked(adapterPosition)
                }

                tvEdit.setOnClickListener {
                    swipeListener.onEditClicked(adapterPosition)
                }

                tvFav.setOnClickListener {
                    swipeListener.onFavoriteClicked(adapterPosition)
                }

                tvDelete.setOnClickListener {
                    swipeListener.onDeleteClicked(adapterPosition)
                }
            }
        }

        fun actionsLogic(position: Int) {
            with(binding) {
                if (position == swipePosition) {
                    llPanel.visibility = View.VISIBLE
                } else {
                    llPanel.visibility = View.GONE
                }
            }
        }

        private fun setFavIcon(isFavorite: Boolean) {
            if (isFavorite) {
                binding.ivStar.setImageResource(R.drawable.star)
            } else {
                binding.ivStar.setImageResource(R.drawable.star_border)
            }
        }
    }
}