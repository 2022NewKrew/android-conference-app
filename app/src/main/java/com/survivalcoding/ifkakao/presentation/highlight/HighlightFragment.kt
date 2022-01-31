package com.survivalcoding.ifkakao.presentation.highlight

import android.os.Bundle
import android.view.View
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import com.survivalcoding.ifkakao.R
import com.survivalcoding.ifkakao.databinding.FragmentHighlightBinding
import com.survivalcoding.ifkakao.presentation.base.BaseFragment
import com.survivalcoding.ifkakao.presentation.base.FooterAdapter
import com.survivalcoding.ifkakao.presentation.detail.DetailFragment
import com.survivalcoding.ifkakao.presentation.highlight.adapter.HighlightHeaderAdapter
import com.survivalcoding.ifkakao.presentation.session.SessionFragment
import com.survivalcoding.ifkakao.presentation.util.SessionItemDecoration
import com.survivalcoding.ifkakao.presentation.util.SessionListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HighlightFragment : BaseFragment<FragmentHighlightBinding>(R.layout.fragment_highlight) {

    private val viewModel: HighlightViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val concatAdapter = ConcatAdapter(
            HighlightHeaderAdapter(
                sessionButtonClickListener = {
                    viewModel.toAllSession()
                    parentFragmentManager.commit {
                        replace(R.id.fragment_container_view, SessionFragment())
                        setReorderingAllowed(true)
                        addToBackStack(null)
                    }
                }
            ),
            SessionListAdapter(
                onClickListener = {
                    viewModel.toDetailSession(it)
                    parentFragmentManager.commit {
                        replace(R.id.fragment_container_view, DetailFragment())
                        setReorderingAllowed(true)
                        addToBackStack(null)
                    }
                }
            ),
            FooterAdapter(
                topButtonClickListener = {
                    bind { fragmentHighlightRecyclerview.smoothScrollToPosition(0) }
                }
            )
        )

        bind {
            adapter = concatAdapter
            itemDecoration = SessionItemDecoration()
            executePendingBindings()
            vm = viewModel
        }
    }
}