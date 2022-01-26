package com.survivalcoding.ifkakao.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.survivalcoding.ifkakao.App
import com.survivalcoding.ifkakao.R
import com.survivalcoding.ifkakao.presentation.highlight.HighlightFragment

class MainActivity : AppCompatActivity() {
    private val viewModel by viewModels<MainViewModel> {
        MainViewModelFactory((application as App).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onBackPressed() {
        viewModel.onBackPressed()
        super.onBackPressed()
    }
}