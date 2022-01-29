package com.survivalcoding.ifkakao.presentation.highlight

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import com.survivalcoding.ifkakao.R
import com.survivalcoding.ifkakao.databinding.FragmentHighlightBinding
import com.survivalcoding.ifkakao.presentation.base.BaseFragment
import com.survivalcoding.ifkakao.presentation.base.FooterAdapter
import com.survivalcoding.ifkakao.presentation.util.FragmentInformation
import com.survivalcoding.ifkakao.presentation.util.SessionItemDecoration
import com.survivalcoding.ifkakao.presentation.util.SessionListAdapter
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class HighlightFragment : BaseFragment<FragmentHighlightBinding>(R.layout.fragment_highlight) {
    @Inject
    lateinit var stk: Stack<FragmentInformation>

    @Inject
    lateinit var sessionItemDecoration: SessionItemDecoration

    private val viewModel: HighlightViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val concatAdapter = ConcatAdapter(
            HighlightHeaderAdapter {

            },
            SessionListAdapter(
                onClickListener = {

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
            itemDecoration = sessionItemDecoration
            executePendingBindings()
            vm = viewModel
        }
    }
}