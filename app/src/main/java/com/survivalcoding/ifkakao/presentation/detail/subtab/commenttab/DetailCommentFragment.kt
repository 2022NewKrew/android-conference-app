package com.survivalcoding.ifkakao.presentation.detail.subtab.commenttab

import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.ViewModelProvider
import com.survivalcoding.ifkakao.R
import com.survivalcoding.ifkakao.databinding.FragmentDetailCommentBinding
import com.survivalcoding.ifkakao.domain.model.IkComment
import com.survivalcoding.ifkakao.presentation.base.BaseFragment
import com.survivalcoding.ifkakao.presentation.detail.DetailEvent
import com.survivalcoding.ifkakao.presentation.detail.DetailViewModel
import com.survivalcoding.ifkakao.presentation.detail.adapter.CommentListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailCommentFragment :
    BaseFragment<FragmentDetailCommentBinding>(R.layout.fragment_detail_comment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val commentAdapter = CommentListAdapter()

        bind { commentRecyclerView.adapter = commentAdapter }

        val viewModel = parentFragment?.let { parent ->
            ViewModelProvider(parent)[DetailViewModel::class.java]
        }

        bind {
            sendButton.setOnClickListener {
                val name = inputCommentName.text.toString()
                val content = inputCommentContent.text.toString()
                var id = -1

                viewModel?.localSessionData?.value?.let {
                    id = it.comments.size
                }
                if (id == -1) return@setOnClickListener

                val newComment = IkComment(id, name, content)
                viewModel?.onEvent(DetailEvent.InsertComment(newComment))

                inputCommentName.setText("")
                inputCommentContent.setText("")

                val inputMethodManager =
                    (requireActivity().getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager)
                inputMethodManager.hideSoftInputFromWindow(inputCommentContent.windowToken, 0)
            }
        }

        viewModel?.localSessionData?.observe(viewLifecycleOwner) {
            commentAdapter.submitList(it.comments)
        }
    }
}