package com.survivalcoding.ifkakao.presentation.highlight

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import com.survivalcoding.ifkakao.App
import com.survivalcoding.ifkakao.R
import com.survivalcoding.ifkakao.databinding.FragmentHighlightBinding
import com.survivalcoding.ifkakao.presentation.FragmentInformation
import com.survivalcoding.ifkakao.presentation.detail.DetailFragment
import com.survivalcoding.ifkakao.presentation.util.SessionListAdapter

class HighlightFragment : Fragment() {
    private var _binding: FragmentHighlightBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<HighlightViewModel> {
        HighlightViewModelFactory((requireActivity().application as App).allSessions)
    }

    private val highlightAdapter by lazy {
        SessionListAdapter(
            onClickListener = {
                (requireActivity().application as App).fragmentStack.push(
                    FragmentInformation(currentSession = it)
                )
                parentFragmentManager.commit {
                    replace(R.id.fragment_container_view, DetailFragment())
                    setReorderingAllowed(true)
                    addToBackStack(null)
                }
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

        binding.rvHighlightSessionsRecyclerview.adapter = highlightAdapter
        binding.highlightUIState = HighlightUIState(viewModel.highlightSessions)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}