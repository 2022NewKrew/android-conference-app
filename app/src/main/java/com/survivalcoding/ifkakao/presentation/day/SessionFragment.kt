package com.survivalcoding.ifkakao.presentation.day

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import com.survivalcoding.ifkakao.R
import com.survivalcoding.ifkakao.databinding.FragmentSessionBinding
import com.survivalcoding.ifkakao.presentation.base.BaseFragment
import com.survivalcoding.ifkakao.presentation.base.FooterAdapter
import com.survivalcoding.ifkakao.presentation.util.ImageResource
import com.survivalcoding.ifkakao.presentation.util.SessionItemDecoration
import com.survivalcoding.ifkakao.presentation.util.SessionListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SessionFragment : BaseFragment<FragmentSessionBinding>(R.layout.fragment_session) {
    private val viewModel: SessionViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val concatAdapter = ConcatAdapter(
            SessionListAdapter(
                threshold = 10,
                load = { viewModel.onEvent(SessionEvent.LoadMoreSessions) },
                onClickListener = {

                }
            ),
            FooterAdapter(
                topButtonClickListener = {
                    bind {
                        fragmentSessionRecyclerview.smoothScrollToPosition(0)
                        appbar.setExpanded(true)
                    }
                }
            )
        )

        bind {
            adapter = concatAdapter
            itemDecoration = SessionItemDecoration()
            imageResource = ImageResource()
            executePendingBindings()
            vm = viewModel
        }
    }
}