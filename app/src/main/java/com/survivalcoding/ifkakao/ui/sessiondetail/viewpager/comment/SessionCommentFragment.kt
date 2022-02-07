package com.survivalcoding.ifkakao.ui.sessiondetail.viewpager.comment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.survivalcoding.ifkakao.databinding.FragmentSessionCommentBinding
import com.survivalcoding.ifkakao.ui.sessiondetail.viewpager.comment.adapter.CommentListAdapter
import com.survivalcoding.ifkakao.ui.sessiondetail.viewpager.comment.adapter.CommentViewHolder

class SessionCommentFragment: Fragment() {

    companion object {
        const val ARG_KEY_SESSION = "session"
    }

    private var _binding: FragmentSessionCommentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSessionCommentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val adapter = CommentListAdapter()
        binding.commentRv.adapter = adapter
        val comments = (0 until 30).map { Comment(it) }
        adapter.submitList(comments)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}