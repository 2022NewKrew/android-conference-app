package com.survivalcoding.ifkakao.presentation.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import com.survivalcoding.ifkakao.App
import com.survivalcoding.ifkakao.R
import com.survivalcoding.ifkakao.databinding.FragmentDetailBinding
import com.survivalcoding.ifkakao.presentation.MainViewModel
import com.survivalcoding.ifkakao.presentation.MainViewModelFactory
import com.survivalcoding.ifkakao.presentation.SessionType
import com.survivalcoding.ifkakao.presentation.detail.adapter.TagListAdapter
import com.survivalcoding.ifkakao.presentation.highlight.adapter.SessionListAdapter

class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val relativeSessionsAdapter by lazy {
        SessionListAdapter(
            clickListener = {
                viewModel.selectSession(it)
                parentFragmentManager.commit {
                    replace(R.id.fragment_container_view, DetailFragment())
                    setReorderingAllowed(true)
                    addToBackStack(null)
                }
            }
        )
    }

    private val tagsAdapter by lazy {
        TagListAdapter(
            onClickListener = {

            }
        )
    }

    private val viewModel by activityViewModels<MainViewModel> {
        MainViewModelFactory((requireActivity().application as App).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        viewModel.setSessionType(SessionType.RelativeSession)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvRelativeSessionsList.adapter = relativeSessionsAdapter
        binding.rvTagList.adapter = tagsAdapter

        viewModel.selectedSession.observe(viewLifecycleOwner) {
            it?.let {
                binding.tvDetailTitle.text = it.title
                binding.tvDetailContent.text = it.content
                binding.tvDetailHashtag.text = it.hashTag
                tagsAdapter.submitList(it.tag)
            }
        }

        viewModel.usedList.observe(viewLifecycleOwner) {
            relativeSessionsAdapter.submitList(it)
        }
    }

    override fun onDestroyView() {
        _binding = null
        viewModel.selectSession(null)
        super.onDestroyView()
    }
}