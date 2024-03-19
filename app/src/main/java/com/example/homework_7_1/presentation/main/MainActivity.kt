package com.example.homework_7_1.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.homework_7_1.R
import com.example.homework_7_1.databinding.ActivityMainBinding
import com.example.homework_7_1.presentation.cam.CamScreenFragment
import com.example.homework_7_1.presentation.door.DoorScreenFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(CamScreenFragment(), "Cameras")
        adapter.addFragment(DoorScreenFragment(), "Doors")

        binding.viewPager.adapter = adapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)
    }
}