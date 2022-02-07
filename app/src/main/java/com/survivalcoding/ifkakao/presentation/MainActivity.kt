package com.survivalcoding.ifkakao.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.survivalcoding.ifkakao.R
import com.survivalcoding.ifkakao.databinding.ActivityMainBinding
import com.survivalcoding.ifkakao.presentation.sessions.SessionsFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.toolbarIvMenu.setOnClickListener { binding.root.openDrawer(binding.navigationView) }
        binding.navigationView.setNavigationItemSelectedListener { menu ->
            when (menu.itemId) {
                R.id.menu_session -> {
                    moveToSessions()
                    binding.root.closeDrawer(binding.navigationView)
                    true
                }
                else -> false
            }
        }
    }

    private fun moveToSessions() {
        supportFragmentManager.commit {
            replace<SessionsFragment>(R.id.fragment_container_view)
            setReorderingAllowed(true)
            addToBackStack(null)
        }
    }
}