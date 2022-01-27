package com.survivalcoding.ifkakao.ui.sessiondetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayoutMediator
import com.survivalcoding.ifkakao.databinding.FragmentSessionDetailBinding
import com.survivalcoding.ifkakao.ui.sessiondetail.viewpager.SessionDetailFragmentStateAdapter
import com.survivalcoding.ifkakao.ui.sessiondetail.viewpager.ViewPager2ViewHeightAnimator

class SessionDetailFragment : Fragment() {

    private var _binding: FragmentSessionDetailBinding? = null
    private val binding get() = _binding!!

    private val args: SessionDetailFragmentArgs by navArgs()

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

        val adapter = SessionDetailFragmentStateAdapter(this, args.session)
        binding.viewPager.adapter = adapter
        ViewPager2ViewHeightAnimator().viewPager2 = binding.viewPager
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = when(position) {
                0 -> {
                    "세션 설명"
                }
                1 -> {
                    "댓글"
                }
                else -> {
                    throw IllegalArgumentException("Only 0 or 1 is allowed but $position was given!")
                }
            }
        }.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}