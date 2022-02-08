package com.survivalcoding.ifkakao

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.lifecycle.lifecycleScope
import com.survivalcoding.ifkakao.dialog.LoginDialogFragment
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

        findViewById<ImageButton>(R.id.exit_button).setOnClickListener {
            findViewById<DrawerLayout>(R.id.drawer).close()
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
                if (!viewModel.isLogin.value) {
                    LoginDialogFragment().show(supportFragmentManager, "login")
                } else {
                    viewModel.setLogout()
                }
                true
            }
            R.id.drawer_menu -> {
                val drawer = findViewById<DrawerLayout>(R.id.drawer)

                if (!drawer.isDrawerOpen(Gravity.LEFT)) drawer.openDrawer(Gravity.LEFT)
                else drawer.closeDrawer(Gravity.LEFT)
                return true
            }
            else -> {
                super.onOptionsItemSelected(item)

            }

        }
    }
}