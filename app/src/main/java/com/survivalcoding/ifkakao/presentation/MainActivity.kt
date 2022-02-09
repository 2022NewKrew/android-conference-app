package com.survivalcoding.ifkakao.presentation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.FragmentManager
import com.survivalcoding.ifkakao.R
import com.survivalcoding.ifkakao.databinding.ActivityMainBinding
import com.survivalcoding.ifkakao.presentation.favorites.FavoritesFragment
import com.survivalcoding.ifkakao.presentation.main.MainFragment
import com.survivalcoding.ifkakao.presentation.session.SessionFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private var drawer: DrawerLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initActionBar()
        initDrawer()

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container_view, MainFragment())
                .commit()
        }
    }

    override fun onBackPressed() {
        if (closeDrawer()) return
        super.onBackPressed()
    }

    private fun initDrawer() {
        drawer = binding.drawerLayout
        binding.drawerCloseButton.setOnClickListener { closeDrawer() }
        binding.drawerSessionButton.setOnClickListener {
            navigateToSessionList()
            closeDrawer()
        }
        binding.drawerFavoritesButton.setOnClickListener {
            navigateToFavorites()
            closeDrawer()
        }
        binding.emailText.setOnClickListener { navigateToContactMail() }
        binding.talkChannelLinearLayout.setOnClickListener { navigateToTalkChannel() }
    }

    private fun initActionBar() {
        setSupportActionBar(binding.toolBar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        binding.titleTextView.setOnClickListener { navigateToMain() }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_tool_bar_menu, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.open_drawer -> {
                openDrawer()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun openDrawer() {
        if (drawer?.isOpen == false) {
            drawer?.openDrawer(GravityCompat.END)
        }
    }

    private fun closeDrawer(): Boolean {
        if (drawer?.isDrawerOpen(GravityCompat.END) == true) {
            drawer?.closeDrawer(GravityCompat.END)
            return true
        }
        return false
    }

    fun navigateToSessionList() {
        val isPopped = supportFragmentManager.popBackStackImmediate(SessionFragment.TAG, 0)
        if (!isPopped) {
            supportFragmentManager.popBackStackImmediate(
                null,
                FragmentManager.POP_BACK_STACK_INCLUSIVE
            )
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_view, SessionFragment())
                .addToBackStack(SessionFragment.TAG)
                .commit()
        }
    }

    private fun navigateToFavorites() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view, FavoritesFragment())
            .addToBackStack(null)
            .commit()
    }

    private fun navigateToTalkChannel() {
        Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.if_kakao_talk_channel))).apply {
            if (resolveActivity(packageManager) != null) {
                startActivity(this)
            }
        }
    }

    private fun navigateToContactMail() {
        Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:")
            putExtra(Intent.EXTRA_EMAIL, arrayOf(getString(R.string.if_kakao_mail)))
            if (resolveActivity(packageManager) != null) {
                startActivity(this)
            }
        }
    }

    private fun navigateToMain() {
        supportFragmentManager.popBackStackImmediate(
            null,
            FragmentManager.POP_BACK_STACK_INCLUSIVE
        )
    }
}