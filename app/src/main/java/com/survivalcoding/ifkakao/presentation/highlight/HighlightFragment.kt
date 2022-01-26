package com.survivalcoding.ifkakao.presentation.highlight

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.survivalcoding.ifkakao.databinding.FragmentHighlightBinding
import com.survivalcoding.ifkakao.presentation.adapter.SessionListAdapter

class HighlightFragment : Fragment() {
    private var _binding: FragmentHighlightBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HighlightViewModel by viewModels()
    private val adapter by lazy {
        SessionListAdapter { }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        _binding = FragmentHighlightBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // main image 설정
        Glide
            .with(requireContext())
            .load("https://t1.kakaocdn.net/service_if_kakao_prod/images/pc/bg_bye_2021.png")
            .into(binding.highlightIvBackground)

        // bye image 설정
        Glide
            .with(requireContext())
            .load("https://t1.kakaocdn.net/service_if_kakao_prod/images/ico_bye_2021.gif")
            .into(binding.highlightIvBye)

        // recyclerView 설정
        binding.highlightRecyclerView.adapter = adapter

        viewModel.sessions.observe(this) { sessions ->
            adapter.submitList(sessions)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}