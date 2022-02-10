package com.survivalcoding.ifkakao.presentation

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.commit
import com.survivalcoding.ifkakao.R
import com.survivalcoding.ifkakao.databinding.ActivityMainBinding
import com.survivalcoding.ifkakao.presentation.dialog.LoginDialogFragment
import com.survivalcoding.ifkakao.presentation.highlight.HighlightFragment
import com.survivalcoding.ifkakao.presentation.liked.LikedFragment
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

    private val viewModel by viewModels<ActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        supportActionBar?.setDisplayShowTitleEnabled(false)

        val pref = getSharedPreferences("pref", Activity.MODE_PRIVATE)
        if (pref != null && pref.contains("loginInfo")) {
            viewModel.onEvent(LoginEvent.IsLogin)
        }

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
                setReorderingAllowed(true)
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
                setReorderingAllowed(true)
                addToBackStack(null)
            }
        }

        binding.navigationMyListItem.setOnClickListener {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
            if (stk.peek().fragmentType == FragmentType.LIKED) return@setOnClickListener

            stk.push(
                FragmentInformation(
                    fragmentType = FragmentType.LIKED,
                )
            )
            supportFragmentManager.commit {
                replace(R.id.fragment_container_view, LikedFragment())
                setReorderingAllowed(true)
                addToBackStack(null)
            }
        }

        viewModel.isLogin.observe(this) {
            if (it == LoginState.SUCCESS) {
                if (viewModel.isMaintainLogin.value == true && !pref.contains("loginInfo")) {
                    with(pref.edit()) {
                        putBoolean("loginInfo", true)
                        commit()
                    }
                }
                binding.loginButtonText.text = "로그인 되었습니다."
                binding.loginButton.setOnClickListener { viewModel.onEvent(LoginEvent.Logout) }
                binding.loginButtonText.setTextColor(Color.parseColor("#ffffff"))
            } else {
                binding.loginButtonText.text = "로그인하세요"
                binding.loginButton.setOnClickListener {
                    val dialog = LoginDialogFragment()
                    dialog.show(supportFragmentManager, "login")
                }
                binding.loginButtonText.setTextColor(Color.parseColor("#a0a0a0"))
            }
        }
    }

    override fun onBackPressed() {
        val top = stk.peek()
        if (top.fragmentType == FragmentType.HIGHLIGHT) finish()
        else {
            stk.pop()
            super.onBackPressed()
        }
    }
}