package com.survivalcoding.ifkakao.presentation.sessions

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ConcatAdapter
import com.google.android.material.navigation.NavigationView
import com.survivalcoding.ifkakao.R
import com.survivalcoding.ifkakao.databinding.FragmentSessionsBinding
import com.survivalcoding.ifkakao.presentation.commons.FooterAdapter
import com.survivalcoding.ifkakao.presentation.commons.SessionAdapter
import com.survivalcoding.ifkakao.presentation.detail.DetailFragment
import com.survivalcoding.ifkakao.presentation.main.MainFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class SessionsFragment : Fragment() {
    private val sessionsViewModel by viewModel<SessionsViewModel>()
    private var _binding: FragmentSessionsBinding? = null
    private val binding get() = _binding!!

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSessionsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = binding.recyclerView
        drawerLayout = binding.drawerLayout
        navigationView = binding.navigationView

        val adapter = ConcatAdapter(
            SessionsHeaderAdapter(onClickTab = { idx ->
                sessionsViewModel.updateSessionsByDay(idx + 1)
            }, onClickFilter = {
                drawerLayout.openDrawer(GravityCompat.END)
            }),
            SessionAdapter(onClickSession = { idx ->
                moveToNextFragment(DetailFragment().apply {
                    this.arguments = bundleOf(MainFragment.SELECTED to idx)
                })
            }),
            FooterAdapter(
                onClickUpButton = {
                    recyclerView.smoothScrollToPosition(0)
                }, onClickSite = {
                    val browserIntent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://if.kakao.com/2020/")
                    )
                    if (browserIntent.resolveActivity(requireActivity().packageManager) != null) {
                        startActivity(browserIntent)
                    }
                }
            )
        )
        recyclerView.adapter = adapter

        setDrawerLayout()

        sessionsViewModel.sessions.observe(viewLifecycleOwner) {
            (adapter.adapters[1] as SessionAdapter).submitList(it)
        }

        sessionsViewModel.day.observe(viewLifecycleOwner) {
            (adapter.adapters[0] as SessionsHeaderAdapter).setTabSelection(it - 1)
        }
    }

    private fun setDrawerLayout() {
        val exitButton = binding.exitButton
        exitButton.setOnClickListener {
            if (drawerLayout.isDrawerOpen(GravityCompat.END)) {
                drawerLayout.closeDrawer(GravityCompat.END)
            }
        }

        /*
        //ToDo: back press 구현
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (drawerLayout.isDrawerOpen(GravityCompat.END)) {
                        drawerLayout.closeDrawer(GravityCompat.END)
                    }
                }
            })*/
    }


    private fun moveToNextFragment(fragment: Fragment) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(
                R.id.fragmentContainerView,
                fragment
            )
            .addToBackStack(null)
            .commit()
    }

}