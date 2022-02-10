package com.survivalcoding.ifkakao

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import com.survivalcoding.ifkakao.databinding.ActivityMainBinding
import com.survivalcoding.ifkakao.databinding.NaviHeaderBinding
import com.survivalcoding.ifkakao.presentation.mylist.MyListFragment
import com.survivalcoding.ifkakao.presentation.sessions.SessionsFragment


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var headerBinding: NaviHeaderBinding

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)

        drawerLayout = binding.drawerLayout
        navigationView = binding.navigationView
        headerBinding = NaviHeaderBinding.bind(navigationView.getHeaderView(0))

        val exitButton = headerBinding.exitButton
        exitButton.setOnClickListener {
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START)
            }
        }

        navigationView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.sessions -> moveToNextFragment(SessionsFragment())
                R.id.mylist -> moveToNextFragment(MyListFragment())
            }

            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START)
            }
            true
        }

        val linkTalkChannel = binding.talkChannelLink
        linkTalkChannel.setOnClickListener {
            val browserIntent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("http://pf.kakao.com/_AAzxgC")
            )
            if (browserIntent.resolveActivity(packageManager) != null) {
                startActivity(browserIntent)
            }
        }
        val ifKakaoEmail = binding.ifKakaoEmail
        ifKakaoEmail.setOnClickListener {
            val email = Intent(Intent.ACTION_SEND).apply {
                type = "*/*"
                putExtra(Intent.EXTRA_EMAIL, "ifkakak@kakao.com")
            }
            if (email.resolveActivity(packageManager) != null) {
                startActivity(email)
            }
        }
    }

    private fun moveToNextFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.fragmentContainerView,
                fragment
            )
            .addToBackStack(null)
            .commit()
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_list, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_list -> {
                drawerLayout.openDrawer(GravityCompat.START)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}