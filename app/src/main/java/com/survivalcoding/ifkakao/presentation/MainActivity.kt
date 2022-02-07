package com.survivalcoding.ifkakao.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.survivalcoding.ifkakao.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.toolbarIvMenu.setOnClickListener { binding.root.openDrawer(binding.navigationView) }
    }
}