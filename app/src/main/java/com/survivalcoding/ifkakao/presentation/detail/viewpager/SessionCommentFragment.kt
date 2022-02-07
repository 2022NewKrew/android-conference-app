package com.survivalcoding.ifkakao.presentation.detail.viewpager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.survivalcoding.ifkakao.databinding.FragmentSessionCommentBinding
import com.survivalcoding.ifkakao.domain.model.Session

class SessionCommentFragment : Fragment() {
    private var _binding: FragmentSessionCommentBinding? = null
    private val binding get() = _binding!!
    private val session by lazy { requireArguments()["session"] as Session }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSessionCommentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}