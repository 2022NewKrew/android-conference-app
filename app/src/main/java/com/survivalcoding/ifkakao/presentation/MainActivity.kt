package com.survivalcoding.ifkakao.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.commit
import com.survivalcoding.ifkakao.R
import com.survivalcoding.ifkakao.databinding.ActivityMainBinding
import com.survivalcoding.ifkakao.presentation.highlight.HighlightFragment
import com.survivalcoding.ifkakao.presentation.session.SessionFragment
import com.survivalcoding.ifkakao.presentation.util.FragmentInformation
import com.survivalcoding.ifkakao.presentation.util.FragmentType
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var stk: Stack<FragmentInformation>

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        supportActionBar?.setDisplayShowTitleEnabled(false)

        binding.exitButton.setOnClickListener {
            if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
                binding.drawerLayout.closeDrawer(GravityCompat.START)
            }
        }

        binding.drawerToggleButton.setOnClickListener {
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }

        binding.toolbarTitle.setOnClickListener {
            if (stk.peek().fragmentType == FragmentType.HIGHLIGHT) return@setOnClickListener
            stk.push(FragmentInformation(fragmentType = FragmentType.HIGHLIGHT))
            supportFragmentManager.commit {
                replace(R.id.fragment_container_view, HighlightFragment())
                addToBackStack(null)
            }
        }

        binding.navigationSessionItem.setOnClickListener {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
            if (stk.peek().fragmentType == FragmentType.SESSION) return@setOnClickListener

            stk.push(
                FragmentInformation(
                    fragmentType = FragmentType.SESSION,
                    selectedDay = 3,
                    exposedListCount = 8
                )
            )
            supportFragmentManager.commit {
                replace(R.id.fragment_container_view, SessionFragment())
                addToBackStack(null)
            }
        }
    }

    override fun onBackPressed() {
        val top = stk.pop()
        if (top.fragmentType == FragmentType.HIGHLIGHT) finish()
        else super.onBackPressed()
    }
}