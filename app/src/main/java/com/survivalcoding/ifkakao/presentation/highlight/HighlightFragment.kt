package com.survivalcoding.ifkakao.presentation.highlight

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import com.survivalcoding.ifkakao.App
import com.survivalcoding.ifkakao.databinding.FragmentHighlightBinding
import com.survivalcoding.ifkakao.presentation.highlight.adapter.HighlightListAdapter

class HighlightFragment : Fragment() {
    private var _binding: FragmentHighlightBinding? = null
    private val binding get() = _binding!!

    private val highlightAdapter by lazy {
        HighlightListAdapter(
            clickListener = { session ->
                // TODO: fragment replace
            }
        )
    }

    private val viewModel by viewModels<HighlightViewModel> {
        HighlightViewModelFactory((requireActivity().application as App).repository)
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

        binding.rvHighlightSessionsRecyclerview.apply {
            adapter = highlightAdapter
            addItemDecoration(DividerItemDecoration(requireContext(), 1))
        }

        binding.ivScrollToTopButton.setOnClickListener {
            binding.backgroundNestedScrollView.scrollTo(0, 0)
        }

        viewModel.state.observe(viewLifecycleOwner) {
            when (it) {
                State.Init -> {
                    viewModel.loadHighlightSessions()
                }
                State.ListLoadingError -> Toast.makeText(
                    requireContext(),
                    "error",
                    Toast.LENGTH_SHORT
                ).show()
                State.NotLoading -> {}
                State.Loading -> {}
            }
        }

        viewModel.list.observe(viewLifecycleOwner) {
            highlightAdapter.submitList(it)
//            Toast.makeText(requireContext(), "${it.size}", Toast.LENGTH_SHORT).show()
        }

        viewModel.message.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}