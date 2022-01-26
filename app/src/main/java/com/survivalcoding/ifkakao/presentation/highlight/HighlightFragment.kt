package com.survivalcoding.ifkakao.presentation.highlight

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.survivalcoding.ifkakao.App
import com.survivalcoding.ifkakao.R
import com.survivalcoding.ifkakao.databinding.FragmentHighlightBinding
import com.survivalcoding.ifkakao.presentation.FragmentInformation
import com.survivalcoding.ifkakao.presentation.detail.DetailFragment
import com.survivalcoding.ifkakao.presentation.util.SessionListAdapter

class HighlightFragment : Fragment() {
    private var _binding: FragmentHighlightBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<HighlightViewModel> {
        HighlightViewModelFactory((requireActivity().application as App).allSessions)
    }

    private val highlightAdapter by lazy {
        SessionListAdapter(
            onClickListener = {
                (requireActivity().application as App).fragmentStack.push(
                    FragmentInformation(
                        currentSession = it
                    )
                )
                parentFragmentManager.commit {
                    replace(R.id.fragment_container_view, DetailFragment())
                    setReorderingAllowed(true)
                    addToBackStack(null)
                }
            }
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHighlightBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvHighlightSessionsRecyclerview.apply {
            adapter = highlightAdapter
        }

        Glide.with(this)
            .load("https://t1.kakaocdn.net/service_if_kakao_prod/images/pc/bg_bye_2021.png")
            .centerCrop()
            .into(binding.tvMainPageBackground)
        Glide.with(this)
            .load(R.drawable.ic_hand)
            .into(binding.ivHandIconGif)

        viewModel.highlightSessions.observe(viewLifecycleOwner) {
            highlightAdapter.submitList(it)
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}