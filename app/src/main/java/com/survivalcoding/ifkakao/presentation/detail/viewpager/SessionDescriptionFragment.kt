package com.survivalcoding.ifkakao.presentation.detail.viewpager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.survivalcoding.ifkakao.databinding.FragmentSessionDescriptionBinding
import com.survivalcoding.ifkakao.domain.model.Session

class SessionDescriptionFragment : Fragment() {
    private var _binding: FragmentSessionDescriptionBinding? = null
    private val binding get() = _binding!!
    private val session by lazy { requireArguments()["session"] as Session }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSessionDescriptionBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}