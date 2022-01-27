package com.survivalcoding.ifkakao.presentation.highlight

import android.os.Bundle
import android.view.View
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import com.survivalcoding.ifkakao.App
import com.survivalcoding.ifkakao.R
import com.survivalcoding.ifkakao.databinding.FragmentHighlightBinding
import com.survivalcoding.ifkakao.presentation.FragmentInformation
import com.survivalcoding.ifkakao.presentation.base.BaseFragment
import com.survivalcoding.ifkakao.presentation.detail.DetailFragment
import com.survivalcoding.ifkakao.presentation.util.SessionListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HighlightFragment : BaseFragment<FragmentHighlightBinding>(R.layout.fragment_highlight) {
    private val viewModel: HighlightViewModel by viewModels()

    private val highlightAdapter by lazy {
        SessionListAdapter(
            onClickListener = {
                (requireActivity().application as App).fragmentStack.push(
                    FragmentInformation(currentSession = it)
                )
                parentFragmentManager.commit {
                    replace(R.id.fragment_container_view, DetailFragment())
                    setReorderingAllowed(true)
                    addToBackStack(null)
                }
            }
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bind {
            vm = viewModel
            adapter = highlightAdapter
        }
    }
}