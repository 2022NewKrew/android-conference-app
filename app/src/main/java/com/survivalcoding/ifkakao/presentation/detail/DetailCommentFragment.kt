package com.survivalcoding.ifkakao.presentation.detail

import androidx.fragment.app.FragmentManager
import com.survivalcoding.ifkakao.R
import com.survivalcoding.ifkakao.databinding.FragmentDetailCommentBinding
import com.survivalcoding.ifkakao.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailCommentFragment(
    private val mFragmentManager: FragmentManager,
) : BaseFragment<FragmentDetailCommentBinding>(R.layout.fragment_detail_comment) {

}