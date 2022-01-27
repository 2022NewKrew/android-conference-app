package com.survivalcoding.ifkakao.presentation.keyword

import android.os.Bundle
import android.view.View
import androidx.fragment.app.commit
import com.survivalcoding.ifkakao.R
import com.survivalcoding.ifkakao.databinding.FragmentKeywordBinding
import com.survivalcoding.ifkakao.presentation.FragmentInformation
import com.survivalcoding.ifkakao.presentation.base.BaseFragment
import com.survivalcoding.ifkakao.presentation.detail.DetailFragment
import com.survivalcoding.ifkakao.presentation.util.SessionItemDecoration
import com.survivalcoding.ifkakao.presentation.util.SessionListAdapter
import java.util.*
import javax.inject.Inject

class KeywordFragment : BaseFragment<FragmentKeywordBinding>(R.layout.fragment_keyword) {
    @Inject
    lateinit var stk: Stack<FragmentInformation>

    @Inject
    lateinit var sessionItemDecoration: SessionItemDecoration

    private val keywordAdapter by lazy {
        SessionListAdapter(
            onClickListener = {
                stk.push(FragmentInformation(currentSession = it))
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

        }
    }
}