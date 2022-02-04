package com.survivalcoding.ifkakao

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.lifecycle.lifecycleScope
import com.survivalcoding.ifkakao.ui.login.LoginDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                replace<MainFragment>(R.id.fragment_container_view)
            }
        }

        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.my_toolbar)
        toolbar.title = "if(kakao)2021"
        toolbar.setTitleTextColor(Color.WHITE)
        setSupportActionBar(findViewById(R.id.my_toolbar))


    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.menu_main, menu)
        val loginItem = menu.findItem(R.id.login_menu)

        lifecycleScope.launch {
            viewModel.isLogin.collect {
                when (it) {
                    true -> {
                        loginItem.title = "로그아웃"
                    }
                    else -> {
                        loginItem.title = "로그인"
                    }

                }
            }
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.login_menu -> {
                LoginDialogFragment().show(supportFragmentManager, "login")
                true
            }
            R.id.drawer_menu -> {
                //todo
                return true
            }
            else -> {
                super.onOptionsItemSelected(item)

            }

        }
    }
}