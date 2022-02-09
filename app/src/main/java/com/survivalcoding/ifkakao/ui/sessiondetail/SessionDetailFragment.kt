package com.survivalcoding.ifkakao.ui.sessiondetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayoutMediator
import com.survivalcoding.ifkakao.databinding.FragmentSessionDetailBinding
import com.survivalcoding.ifkakao.ui.main.adapter.SessionAdapter
import com.survivalcoding.ifkakao.ui.session.SessionFilter
import com.survivalcoding.ifkakao.ui.session.SessionFragmentDirections
import com.survivalcoding.ifkakao.ui.session.SessionViewModel
import com.survivalcoding.ifkakao.ui.sessiondetail.viewpager.detail.SessionDetailFragmentStateAdapter
import com.survivalcoding.ifkakao.ui.sessiondetail.viewpager.detail.SessionDetailViewModel
import com.survivalcoding.ifkakao.ui.sessiondetail.viewpager.detail.ViewPager2ViewHeightAnimator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SessionDetailFragment : Fragment() {

    private var _binding: FragmentSessionDetailBinding? = null
    private val binding get() = _binding!!

    private val args: SessionDetailFragmentArgs by navArgs()

    private val viewModel: SessionDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSessionDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Glide.with(this).load(args.session.linkList.PC_IMAGE.first().url).into(binding.videoIv)

        val viewPagerAdapter = SessionDetailFragmentStateAdapter(this, args.session)
        binding.viewPager.adapter = viewPagerAdapter
        ViewPager2ViewHeightAnimator().viewPager2 = binding.viewPager
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = when(position) {
                0 -> "세션 설명"
                1 -> "댓글"
                else -> throw IllegalArgumentException("Only 0 or 1 is allowed but $position was given!")
            }
        }.attach()

        val recyclerAdapter = SessionAdapter {
            findNavController().navigate(
                SessionDetailFragmentDirections.actionSessionDetailFragmentSelf(it)
            )
        }
        binding.relatedRv.adapter = recyclerAdapter
        val decoration = DividerItemDecoration(requireContext(), LinearLayout.VERTICAL)
        binding.relatedRv.addItemDecoration(decoration)

        binding.footerIcl.scrollToTopIbt.setOnClickListener {
            binding.nestedScrollView.smoothScrollTo(0, 0)
        }

        viewModel.getRelateSessions(
            SessionFilter(
                3,
                mapOf(args.session.field to true),
                args.session.relationList.CLASSIFICATION.associateWith { true },
                args.session.relationList.TECH_CLASSIFICATION.associateWith { true },
                mapOf(args.session.companyName to true)
            )
        )

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.relatedSessions.collectLatest {
                    recyclerAdapter.submitList(it)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}