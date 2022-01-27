package com.survivalcoding.ifkakao

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private val userSettings by lazy {
        UserSettings(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        Log.d("MainActivity", "onCreate: ${userSettings.userName}")
        Toast.makeText(this, userSettings.userName, Toast.LENGTH_SHORT).show()
    }
}