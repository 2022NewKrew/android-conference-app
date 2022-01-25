package com.survivalcoding.ifkakao.presentation.highlight

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.survivalcoding.ifkakao.App
import com.survivalcoding.ifkakao.databinding.FragmentHighlightBinding
import com.survivalcoding.ifkakao.presentation.MainViewModel
import com.survivalcoding.ifkakao.presentation.MainViewModelFactory
import com.survivalcoding.ifkakao.presentation.SessionType
import com.survivalcoding.ifkakao.presentation.highlight.adapter.HighlightListAdapter

class HighlightFragment : Fragment() {
    private var _binding: FragmentHighlightBinding? = null
    private val binding get() = _binding!!

    private val viewModel by activityViewModels<MainViewModel> {
        MainViewModelFactory((requireActivity().application as App).repository)
    }

    private val adapter by lazy {
        HighlightListAdapter(
            clickListener = {
                // TODO
            }
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHighlightBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.setSessonType(SessionType.HighlightSession)

        binding.rvHighlightSessionsRecyclerview.adapter = adapter

        viewModel.usedList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}