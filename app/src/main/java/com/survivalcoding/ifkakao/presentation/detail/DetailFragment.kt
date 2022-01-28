package com.survivalcoding.ifkakao.presentation.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.survivalcoding.ifkakao.databinding.FragmentDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : Fragment() {
    private val detailViewModel by viewModel<DetailViewModel>()
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

        binding.lifecycleOwner = this
        binding.viewModel = detailViewModel

        //ToDo: Exoplayer 활용한 동영상 뷰 구현
        val playerView = binding.playerView

        val viewpager = binding.viewPager2
        viewpager.adapter = DetailAdapter(this)
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

const val ARG_OBJECT = "object"