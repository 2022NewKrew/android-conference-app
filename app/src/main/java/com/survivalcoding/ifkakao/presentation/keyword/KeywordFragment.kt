package com.survivalcoding.ifkakao.presentation.keyword

import android.os.Bundle
import android.view.View
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import com.survivalcoding.ifkakao.R
import com.survivalcoding.ifkakao.databinding.FragmentKeywordBinding
import com.survivalcoding.ifkakao.presentation.base.BaseFragment
import com.survivalcoding.ifkakao.presentation.base.FooterAdapter
import com.survivalcoding.ifkakao.presentation.detail.DetailFragment
import com.survivalcoding.ifkakao.presentation.keyword.adapter.KeywordHeaderAdapter
import com.survivalcoding.ifkakao.presentation.util.FragmentInformation
import com.survivalcoding.ifkakao.presentation.util.FragmentType
import com.survivalcoding.ifkakao.presentation.util.SessionItemDecoration
import com.survivalcoding.ifkakao.presentation.util.SessionListAdapter
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class KeywordFragment : BaseFragment<FragmentKeywordBinding>(R.layout.fragment_keyword) {
    @Inject
    lateinit var stk: Stack<FragmentInformation>

    private val viewModel: KeywordViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val keywordAdapter = ConcatAdapter(
            KeywordHeaderAdapter(stk.peek().selectedKeyword),
            SessionListAdapter(
                threshold = 0,
                load = { viewModel.onEvent(KeywordEvent.LoadMoreSessions) },
                onClickListener = {
                    stk.push(FragmentInformation(fragmentType = FragmentType.DETAIL, session = it))
                    parentFragmentManager.commit {
                        replace(R.id.fragment_container_view, DetailFragment())
                        setReorderingAllowed(true)
                        addToBackStack(null)
                    }
                }
            ),
            FooterAdapter(
                topButtonClickListener = {
                    bind {
                        fragmentKeywordRecyclerview.scrollToPosition(0)
                    }
                }
            ),
        )

        bind {
            vm = viewModel
            adapter = keywordAdapter
            itemDecoration = SessionItemDecoration()
        }

        viewModel.relatedSessions.observe(viewLifecycleOwner) {
            (keywordAdapter.adapters[1] as SessionListAdapter).submitList(it)
        }
    }
}