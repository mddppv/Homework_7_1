package com.example.homework_7_1.ui.cam

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.homework_7_1.R
import com.example.homework_7_1.databinding.FragmentCamScreenBinding
import com.example.homework_7_1.model.CamModel

class CamScreenFragment : Fragment() {

    private lateinit var binding: FragmentCamScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentCamScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val data = listOf(
            CamModel(
                R.drawable.ic_launcher_background, "Camera 1"
            ), CamModel(
                R.drawable.ic_launcher_foreground, "Camera 2"
            ), CamModel(
                R.drawable.ic_launcher_background, "Camera 3"
            )
        )

        val adapter = CamAdapter(data)
        binding.rvCam.adapter = adapter
    }
}