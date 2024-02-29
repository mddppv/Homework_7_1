package com.example.homework_7_1.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.homework_7_1.databinding.ActivityMainBinding
import com.example.homework_7_1.ui.cam.CamScreenFragment
import com.example.homework_7_1.ui.door.DoorScreenFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(CamScreenFragment(), "Camera")
        adapter.addFragment(DoorScreenFragment(), "Doors")

        binding.viewPager.adapter = adapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)
    }
}