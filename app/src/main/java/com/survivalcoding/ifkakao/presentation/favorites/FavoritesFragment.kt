package com.survivalcoding.ifkakao.presentation.favorites

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.survivalcoding.ifkakao.R
import com.survivalcoding.ifkakao.databinding.FragmentFavoritesBinding
import com.survivalcoding.ifkakao.presentation.common.CommonAdapter
import com.survivalcoding.ifkakao.presentation.common.StickyFooterItemDecoration
import com.survivalcoding.ifkakao.presentation.sessiondetail.SessionDetailFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavoritesFragment : Fragment() {

    private val viewModel: FavoritesViewModel by viewModels()
    private var binding: FragmentFavoritesBinding? = null
    private val adapter: CommonAdapter by lazy {
        CommonAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoritesBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.recyclerView?.adapter = adapter
        binding?.recyclerView?.addItemDecoration(StickyFooterItemDecoration())

        observe()
    }

    override fun onStart() {
        super.onStart()
        viewModel.refreshList()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun observe() {
        repeatOnStart {
            viewModel.eventFlow.collect {
                handleEvent(it)
            }
        }

        repeatOnStart {
            viewModel.uiState.collectLatest {
                adapter.submitList(it.binderList)
            }
        }
    }

    private fun handleEvent(event: FavoritesViewModel.Event) {
        when (event) {
            is FavoritesViewModel.Event.NavigateToSessionDetail -> navigateToSessionDetail(event.idx)
        }
    }

    private fun navigateToSessionDetail(idx: Int) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view, SessionDetailFragment.newInstance(idx))
            .addToBackStack(null).commit()
    }

    private fun repeatOnStart(block: suspend CoroutineScope.() -> Unit) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED, block)
        }
    }
}