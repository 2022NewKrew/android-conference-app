package com.survivalcoding.ifkakao.presentation.highlight

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import androidx.recyclerview.widget.DividerItemDecoration
import com.bumptech.glide.Glide
import com.survivalcoding.ifkakao.App
import com.survivalcoding.ifkakao.R
import com.survivalcoding.ifkakao.databinding.FragmentHighlightBinding
import com.survivalcoding.ifkakao.presentation.MainViewModel
import com.survivalcoding.ifkakao.presentation.MainViewModelFactory
import com.survivalcoding.ifkakao.presentation.SessionType
import com.survivalcoding.ifkakao.presentation.detail.DetailFragment
import com.survivalcoding.ifkakao.presentation.highlight.adapter.SessionListAdapter

class HighlightFragment : Fragment() {
    private var _binding: FragmentHighlightBinding? = null
    private val binding get() = _binding!!

    private val viewModel by activityViewModels<MainViewModel> {
        MainViewModelFactory((requireActivity().application as App).repository)
    }

    private val highlightAdapter by lazy {
        SessionListAdapter(
            onClickListener = {
                viewModel.nextSession(it)
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

        viewModel.initViewModel(SessionType.HighlightSession)

        binding.rvHighlightSessionsRecyclerview.apply {
            adapter = highlightAdapter
            addItemDecoration(DividerItemDecoration(requireContext(), LinearLayout.VERTICAL))
        }

        Glide.with(this)
            .load("https://t1.kakaocdn.net/service_if_kakao_prod/images/pc/bg_bye_2021.png")
            .centerCrop()
            .into(binding.tvMainPageBackground)
        Glide.with(this)
            .load(R.drawable.ic_hand)
            .into(binding.ivHandIconGif)

        viewModel.usedList.observe(viewLifecycleOwner) {
            highlightAdapter.submitList(it)
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}