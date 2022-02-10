package com.survivalcoding.ifkakao.presentation.detail.viewpager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.survivalcoding.ifkakao.databinding.FragmentSessionCommentBinding
import com.survivalcoding.ifkakao.domain.model.Comment
import com.survivalcoding.ifkakao.domain.model.Session
import com.survivalcoding.ifkakao.presentation.detail.adapter.CommentListAdapter

class SessionCommentFragment : Fragment() {
    private var _binding: FragmentSessionCommentBinding? = null
    private val binding get() = _binding!!
    private val session by lazy { requireArguments()["session"] as Session }
    private val adapter by lazy { CommentListAdapter() }
    private val comments = listOf<Comment>(
        Comment(0, "라이언", "좋아요 편하고", 0, 0),
        Comment(1, "어피치", "솔직히 지갑 서비스가 처음에 편한지 잘 몰랐는데, 코로나 때문에 편하다는 걸 깨달았습니다.", 0, 0),
        Comment(2, "무지", "무궁한 발전을 기원합니다.\n감사합니다.", 0, 0),
        Comment(3, "Jay-G", "기능이 생각보다 많네요", 0, 0),
        Comment(4, "콘", "몰랐던 기능들이 엄청 많네요. 잘 사용해 보겠습니다. 고맙습니다.", 0, 0),
        Comment(5, "튜브", "공감합니다. 무한 발전 기대합니다.", 0, 0),
        Comment(6, "네오", "좋아요", 0, 0),
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSessionCommentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 댓글 recycler view 설정
        binding.sessionCommentRecyclerView.adapter = adapter
        adapter.submitList(comments)
    }

    override fun onResume() {
        super.onResume()
        binding.root.requestLayout()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}