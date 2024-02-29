package com.example.homework_7_1.ui.door

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.homework_7_1.R
import com.example.homework_7_1.databinding.FragmentDoorScreenBinding
import com.example.homework_7_1.model.DoorModel
import com.example.homework_7_1.util.ItemClickListener

class DoorScreenFragment : Fragment(), ItemClickListener {

    private lateinit var binding: FragmentDoorScreenBinding
    private lateinit var adapter: DoorAdapter
    private var data = listOf(
        DoorModel(R.drawable.ic_launcher_foreground, "Door 1", "Unlocked"),
        DoorModel(R.drawable.ic_launcher_background, "Door 2", "Closed"),
        DoorModel(R.drawable.ic_launcher_background, "Door 3", "Locked"),
        DoorModel(R.drawable.ic_launcher_foreground, "Door 4", "Closed"),
        DoorModel(R.drawable.ic_launcher_background, "Door 5", "Closed"),
        DoorModel(R.drawable.ic_launcher_foreground, "Door 6", "Unlocked"),
        DoorModel(R.drawable.ic_launcher_foreground, "Door 7", "Locked"),
        DoorModel(R.drawable.ic_launcher_background, "Door 8", "Closed"),
        DoorModel(R.drawable.ic_launcher_foreground, "Door 9", "Locked"),
        DoorModel(R.drawable.ic_launcher_background, "Door 10", "Unlocked"),
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentDoorScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = DoorAdapter(data, this)
        binding.rvDoor.adapter = adapter
    }

    override fun onItemClicked(position: Int) {
        val clickedItem = data[position]
        clickedItem.isVisible = !clickedItem.isVisible
        adapter.notifyItemChanged(position)
    }
}