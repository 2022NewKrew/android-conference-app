package com.survivalcoding.ifkakao.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.survivalcoding.ifkakao.App
import com.survivalcoding.ifkakao.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onBackPressed() {
        (application as App).fragmentStack.pop()
        super.onBackPressed()
    }
}