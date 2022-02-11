package com.survivalcoding.ifkakao.presentation.detail

import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.google.android.material.tabs.TabLayoutMediator
import com.survivalcoding.ifkakao.databinding.FragmentDetailBinding
import org.koin.androidx.viewmodel.ext.android.stateViewModel


class DetailFragment : Fragment() {
    private val detailViewModel: DetailViewModel by stateViewModel(state = { requireArguments() })
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val playerTitle = binding.playerTitle

        detailViewModel.session.observe(viewLifecycleOwner) { session ->
            playerTitle.text = session.title

            session.linkList?.PC_IMAGE?.get(0)?.url?.let {
                Glide.with(requireContext())
                    .load(it)
                    .into(object : CustomTarget<Drawable>() {
                        override fun onResourceReady(
                            resource: Drawable,
                            transition: Transition<in Drawable>?
                        ) {
                            binding.playerView.background = resource
                        }

                        override fun onLoadCleared(placeholder: Drawable?) {}
                    })
            }

            binding.playButton.setOnClickListener {
                val browserIntent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(session.linkList?.VIDEO?.get(0)?.url)
                )
                if (browserIntent.resolveActivity(requireActivity().packageManager) != null) {
                    startActivity(browserIntent)
                }
            }
        }


        val viewpager = binding.viewPager2
        viewpager.adapter = DetailAdapter(this)

        val tabLayout = binding.tabLayout
        TabLayoutMediator(tabLayout, viewpager) { tab, position ->
            tab.text = if(position == 0) "관련 설명"
            else "댓글"
        }.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

class DetailAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return if (position == 0) DetailExplanationFragment()
        else DetailCommentFragment()
    }
}