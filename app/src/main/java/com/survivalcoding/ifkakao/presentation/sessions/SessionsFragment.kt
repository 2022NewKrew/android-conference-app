package com.survivalcoding.ifkakao.presentation.sessions

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.google.android.material.tabs.TabLayoutMediator
import com.survivalcoding.ifkakao.databinding.FragmentSessionsBinding
import com.survivalcoding.ifkakao.presentation.sessions.viewpager.SessionsPagerAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SessionsFragment : Fragment() {
    private var _binding: FragmentSessionsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SessionsViewModel by viewModels()
    private val filterDialog: DialogFragment by lazy { FilterDialogFragment() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSessionsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.sessionsViewPager.adapter = SessionsPagerAdapter(this)

        TabLayoutMediator(binding.sessionsTabLayout, binding.sessionsViewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "Day1"
                1 -> "Day2"
                2 -> "Day3(All)"
                else -> throw Exception()
            }
        }.attach()

        binding.sessionsTvFilter.setOnClickListener {
            filterDialog.show(
                parentFragmentManager,
                "FilterDialog"
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}