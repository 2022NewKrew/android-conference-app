package com.survivalcoding.ifkakao.presentation.detail.subtab.commenttab

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.survivalcoding.ifkakao.R
import com.survivalcoding.ifkakao.databinding.FragmentDetailCommentBinding
import com.survivalcoding.ifkakao.domain.model.IkComment
import com.survivalcoding.ifkakao.presentation.base.BaseFragment
import com.survivalcoding.ifkakao.presentation.detail.DetailViewModel
import com.survivalcoding.ifkakao.presentation.detail.adapter.CommentListAdapter
import com.survivalcoding.ifkakao.presentation.util.FragmentInformation
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class DetailCommentFragment :
    BaseFragment<FragmentDetailCommentBinding>(R.layout.fragment_detail_comment) {

    @Inject
    lateinit var stk: Stack<FragmentInformation>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val commentAdapter = CommentListAdapter().apply {
            submitList(
                List(15) { IkComment(it, "kkk", content = "$it 번째 코멘트") }
            )
        }

        bind { commentRecyclerView.adapter = commentAdapter }

        val viewModel = parentFragment?.let { parent ->
            ViewModelProvider(parent)[DetailViewModel::class.java]
        }

        bind {
            sendButton.setOnClickListener {
                val name = inputCommentName.text.toString()
                val content = inputCommentContent.text.toString()
                val newComment = IkComment(
                    id = -1,
                    name = name,
                    content = content,
                )
            }
        }
    }
}