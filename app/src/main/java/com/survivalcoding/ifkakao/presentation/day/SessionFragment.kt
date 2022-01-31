package com.survivalcoding.ifkakao.presentation.day

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import com.survivalcoding.ifkakao.R
import com.survivalcoding.ifkakao.databinding.FragmentSessionBinding
import com.survivalcoding.ifkakao.presentation.base.BaseFragment
import com.survivalcoding.ifkakao.presentation.base.FooterAdapter
import com.survivalcoding.ifkakao.presentation.detail.DetailFragment
import com.survivalcoding.ifkakao.presentation.dialog.KeywordDialogFragment
import com.survivalcoding.ifkakao.presentation.util.Resource
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
                threshold = 0,
                load = { viewModel.onEvent(SessionEvent.LoadMoreSessions) },
                onClickListener = {
                    viewModel.onEvent(SessionEvent.NextSession(it))
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
                        fragmentSessionRecyclerview.scrollToPosition(0)
                        appbar.setExpanded(true)
                    }
                }
            )
        )

        bind {
            adapter = concatAdapter
            itemDecoration = SessionItemDecoration()
            imageResource = Resource()
            executePendingBindings()
            vm = viewModel

            selectDayTab.setOnClickListener {
                val days = resources.getStringArray(R.array.days)
                val builder = AlertDialog.Builder(requireActivity())
                builder.setItems(days) { _, which ->
                    viewModel.onEvent(SessionEvent.ChangeDay(which + 1))
                    appbar.setExpanded(false)
                    fragmentSessionRecyclerview.smoothScrollToPosition(0)
                }
                builder.create().show()
            }

            selectKeywordButton.setOnClickListener {
                val dialog = KeywordDialogFragment(viewModel.isChanged)
                dialog.setStyle(DialogFragment.STYLE_NO_TITLE, android.R.style.Theme_Holo_Light)
                dialog.show(parentFragmentManager, "tag")
            }
        }

        viewModel.isChanged.observe(viewLifecycleOwner) {
            if (it) {
                viewModel.onEvent(SessionEvent.Update)
                viewModel.isChanged.value = false
                bind { appbar.setExpanded(false) }
            }
        }
    }
}