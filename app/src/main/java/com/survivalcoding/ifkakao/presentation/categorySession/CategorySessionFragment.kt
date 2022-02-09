package com.survivalcoding.ifkakao.presentation.categorySession

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.survivalcoding.ifkakao.R
import com.survivalcoding.ifkakao.databinding.FragmentCategorySessionBinding
import com.survivalcoding.ifkakao.domain.entity.Categories
import com.survivalcoding.ifkakao.domain.entity.Session
import com.survivalcoding.ifkakao.presentation.common.CommonAdapter
import com.survivalcoding.ifkakao.presentation.common.StickyFooterItemDecoration
import com.survivalcoding.ifkakao.presentation.sessiondetail.SessionDetailFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CategorySessionFragment : Fragment() {

    private val viewModel: CategorySessionViewModel by viewModels()

    private var binding: FragmentCategorySessionBinding? = null

    private val adapter: CommonAdapter by lazy {
        CommonAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            viewModel.refreshTitleAndSessions(
                it.getString(TITLE) ?: "",
                it.getParcelable(CATEGORIES) ?: Categories()
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCategorySessionBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.recyclerView?.adapter = adapter
        binding?.recyclerView?.addItemDecoration(StickyFooterItemDecoration())

        observe()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun observe() {
        repeatOnStart {
            viewModel.uiState.collect {
                adapter.submitList(it.binderList)
            }
        }

        repeatOnStart {
            viewModel.eventFlow.collect {
                handleEvent(it)
            }
        }
    }

    private fun handleEvent(event: CategorySessionViewModel.Event) {
        when (event) {
            is CategorySessionViewModel.Event.NavigateToSessionDetail -> navigateToSessionDetail(
                event.session
            )
        }
    }

    private fun navigateToSessionDetail(session: Session) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view, SessionDetailFragment.newInstance(session.idx))
            .addToBackStack(null)
            .commit()
    }

    private fun repeatOnStart(block: suspend CoroutineScope.() -> Unit) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED, block)
        }
    }

    companion object {
        const val CATEGORIES = "categories"
        const val TITLE = "title"

        @JvmStatic
        fun newInstance(categories: Categories, title: String) =
            CategorySessionFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(CATEGORIES, categories)
                    putString(TITLE, title)
                }
            }
    }
}