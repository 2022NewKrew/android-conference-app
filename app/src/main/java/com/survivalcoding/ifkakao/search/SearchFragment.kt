package com.survivalcoding.ifkakao.search

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.survivalcoding.ifkakao.MainViewModel
import com.survivalcoding.ifkakao.R
import com.survivalcoding.ifkakao.adapter.SessionListAdapter
import com.survivalcoding.ifkakao.compose.SessionFragment
import com.survivalcoding.ifkakao.databinding.FragmentSearchBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SearchFragment : Fragment() {

    private val viewModel by activityViewModels<MainViewModel>()
    private var _binding: FragmentSearchBinding? = null
    val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Glide.with(this).load(R.drawable.vod_teaser_2021_pc).into(binding.searchViewMainImage)
        } else {
            Glide.with(this).load(R.drawable.vod_teaser_2021_mobile)
                .into(binding.searchViewMainImage)
        }

        binding.viewPager.adapter = FragmentAdapter(requireActivity())

    }


}