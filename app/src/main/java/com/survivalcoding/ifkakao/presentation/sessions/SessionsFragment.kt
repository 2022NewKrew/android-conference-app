package com.survivalcoding.ifkakao.presentation.sessions

import android.net.Uri
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

        // 영상 재생
        binding.sessionsBannerVideoView.setVideoURI(Uri.parse("https://t1.kakaocdn.net/service_if_kakao_prod/videos/mo/vod_teaser_2021.mp4"))
        binding.sessionsBannerVideoView.setOnPreparedListener {
            it.isLooping = true
            binding.sessionsBannerVideoView.start()
            binding.sessionsBannerThumbnail.visibility = View.INVISIBLE
        }

        binding.sessionsTvFilter.setOnClickListener {
            viewModel.setTmpFilter()
            filterDialog.show(
                childFragmentManager,
                "FilterDialog"
            )
        }
        viewModel.filterCount.observe(this) {
            binding.sessionsTvFilter.text = if (it == 0) "" else it.toString()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}