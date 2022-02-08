package com.survivalcoding.ifkakao.presentation.sessions

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ConcatAdapter
import com.google.android.flexbox.FlexboxLayout
import com.google.android.material.navigation.NavigationView
import com.survivalcoding.ifkakao.R
import com.survivalcoding.ifkakao.databinding.FragmentSessionsBinding
import com.survivalcoding.ifkakao.domain.model.companyFilterList
import com.survivalcoding.ifkakao.domain.model.fieldFilterList
import com.survivalcoding.ifkakao.domain.model.sbFilterList
import com.survivalcoding.ifkakao.domain.model.techFilterList
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
        val fieldFilter = binding.fieldButtonLayout
        val sbFilter = binding.sbButtonLayout
        val techFilter = binding.techButtonLayout
        val companyFilter = binding.companyButtonLayout

        addButtons(fieldFilter, fieldFilterList, FIELD)
        addButtons(sbFilter, sbFilterList, SB)
        addButtons(techFilter, techFilterList, TECH)
        addButtons(companyFilter, companyFilterList, COMPANY)

        sessionsViewModel.fieldFilter.observe(viewLifecycleOwner) {
            setButtonsSelected(it, fieldFilterList)
        }
        sessionsViewModel.sbFilter.observe(viewLifecycleOwner) {
            setButtonsSelected(it, sbFilterList)
        }
        sessionsViewModel.techFilter.observe(viewLifecycleOwner) {
            setButtonsSelected(it, techFilterList)
        }
        sessionsViewModel.companyFilter.observe(viewLifecycleOwner) {
            setButtonsSelected(it, companyFilterList)
        }

        val exitButton = binding.exitButton
        exitButton.setOnClickListener {
            sessionsViewModel.initializeFilters()
            exitFilter()
        }

        binding.initializeButton.setOnClickListener {
            sessionsViewModel.resetFilters()
            exitFilter()
        }

        binding.completeButton.setOnClickListener {
            sessionsViewModel.updateFilters()
            exitFilter()
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

    private fun setButtonsSelected(list: List<String>, filterList: List<String>) {
        for (str in filterList) {
            binding.root.findViewWithTag<Button>(str).isSelected = false
        }
        for (str in list) {
            binding.root.findViewWithTag<Button>(str).isSelected = true
        }
    }

    private fun exitFilter() {
        if (drawerLayout.isDrawerOpen(GravityCompat.END)) {
            drawerLayout.closeDrawer(GravityCompat.END)
        }
    }

    private fun addButtons(layout: FlexboxLayout, list: List<String>, update: Int) {
        for (str in list) {
            layout.addView(
                Button(
                    ContextThemeWrapper(
                        requireContext(),
                        R.style.filterButton
                    )
                ).apply {
                    tag = str
                    text = str
                    background =
                        ContextCompat.getDrawable(requireContext(), R.drawable.bg_button_filter)
                    setOnClickListener {
                        when (update) {
                            FIELD -> sessionsViewModel.updateFieldFilter(str, !isSelected)
                            SB -> sessionsViewModel.updateSbFilter(str, !isSelected)
                            TECH -> sessionsViewModel.updateTechFilter(str, !isSelected)
                            COMPANY -> sessionsViewModel.updateCompanyFilter(str, !isSelected)
                        }
                    }
                })
        }
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

    companion object {
        const val FIELD = 0
        const val SB = 1
        const val TECH = 2
        const val COMPANY = 3
    }

}