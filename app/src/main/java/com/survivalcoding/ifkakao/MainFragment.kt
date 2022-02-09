package com.survivalcoding.ifkakao

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ScrollView
import androidx.fragment.app.*
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.survivalcoding.ifkakao.search.SearchFragment
import com.survivalcoding.ifkakao.adapter.SessionListAdapter
import com.survivalcoding.ifkakao.compose.SessionFragment
import com.survivalcoding.ifkakao.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    val binding get() = _binding!!

    private val viewModel by activityViewModels<MainViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = SessionListAdapter(
            onSessionClicked = { data ->
                viewModel.saveClickedSession(data)
                parentFragmentManager.commit {
                    replace<SessionFragment>(R.id.fragment_container_view, "session")
                    addToBackStack(null)
                }
            },
            onLikeClicked = { idx, isLike ->
                viewModel.toggleLike(idx, isLike)
            },
            viewModel = viewModel
        )

        binding.mainRecyclerview.adapter = adapter
        binding.mainRecyclerview.layoutManager = LinearLayoutManager(requireContext())

        Glide.with(this).load(R.drawable.ico_bye_2021).into(binding.handWave)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.highlightItems.collect {
                    adapter.submitList(it)
                }
            }
        }

        binding.totalSessionView.setOnClickListener {
            parentFragmentManager.commit {
                replace<SearchFragment>(R.id.fragment_container_view, "search")
                addToBackStack(null)
            }
        }

        binding.scrollTopView.scrollTopImage.setOnClickListener {
            binding.mainNestedScrollView.fullScroll(ScrollView.FOCUS_UP)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}