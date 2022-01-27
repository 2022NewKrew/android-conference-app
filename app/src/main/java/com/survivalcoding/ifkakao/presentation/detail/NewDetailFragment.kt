package com.survivalcoding.ifkakao.presentation.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import com.survivalcoding.ifkakao.R
import com.survivalcoding.ifkakao.databinding.FragmentDetailBinding
import com.survivalcoding.ifkakao.presentation.FragmentInformation
import com.survivalcoding.ifkakao.presentation.base.BaseFragment
import com.survivalcoding.ifkakao.presentation.detail.adapter.SpeakerListAdapter
import com.survivalcoding.ifkakao.presentation.detail.adapter.TagListAdapter
import com.survivalcoding.ifkakao.presentation.util.SessionItemDecoration
import com.survivalcoding.ifkakao.presentation.util.SessionListAdapter
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class NewDetailFragment : BaseFragment<FragmentDetailBinding>(R.layout.fragment_detail) {
    @Inject
    lateinit var stk: Stack<FragmentInformation>

    @Inject
    lateinit var sessionItemDecoration: SessionItemDecoration

    private val viewModel: NewDetailViewModel by viewModels()

    private val tagAdapter by lazy {
        TagListAdapter(
            onClickListener = {

            }
        )
    }

    private val sessionAdapter by lazy {
        SessionListAdapter(
            onClickListener = {
                val top = stk.pop()
                stk.push(top.copy(relatedSessionsCount = viewModel.getCount()))
                parentFragmentManager.commit {
                    replace(R.id.fragment_container_view, DetailFragment())
                    setReorderingAllowed(true)
                    addToBackStack(null)
                }
            }
        )
    }

    private val speakerListAdapter by lazy { SpeakerListAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.onEvent(DetailEvent.LoadingData(stk.peek()))

        bind {

        }
    }
}