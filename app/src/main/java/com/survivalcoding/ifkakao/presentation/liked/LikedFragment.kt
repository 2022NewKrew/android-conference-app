package com.survivalcoding.ifkakao.presentation.liked

import android.os.Bundle
import android.view.View
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import com.survivalcoding.ifkakao.R
import com.survivalcoding.ifkakao.databinding.FragmentLikedBinding
import com.survivalcoding.ifkakao.presentation.base.BaseFragment
import com.survivalcoding.ifkakao.presentation.base.FooterAdapter
import com.survivalcoding.ifkakao.presentation.detail.DetailFragment
import com.survivalcoding.ifkakao.presentation.liked.adapter.LikedHeaderAdapter
import com.survivalcoding.ifkakao.presentation.util.FragmentInformation
import com.survivalcoding.ifkakao.presentation.util.FragmentType
import com.survivalcoding.ifkakao.presentation.util.SessionItemDecoration
import com.survivalcoding.ifkakao.presentation.util.SessionListAdapter
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class LikedFragment : BaseFragment<FragmentLikedBinding>(R.layout.fragment_liked) {
    @Inject
    lateinit var stk: Stack<FragmentInformation>

    private val viewModel: LikedViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val likedAdapter = ConcatAdapter(
            LikedHeaderAdapter(),
            SessionListAdapter(
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
                        fragmentLikedRecyclerview.scrollToPosition(0)
                    }
                }
            ),
        )

        bind {
            adapter = likedAdapter
            itemDecoration = SessionItemDecoration()
        }

        viewModel.likedSession.observe(viewLifecycleOwner) {
            (likedAdapter.adapters[1] as SessionListAdapter).submitList(it)
        }
    }
}