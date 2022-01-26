package com.survivalcoding.ifkakao.presentation.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import com.survivalcoding.ifkakao.App
import com.survivalcoding.ifkakao.R
import com.survivalcoding.ifkakao.databinding.FragmentDetailBinding
import com.survivalcoding.ifkakao.presentation.MainViewModel
import com.survivalcoding.ifkakao.presentation.MainViewModelFactory
import com.survivalcoding.ifkakao.presentation.FragmentType
import com.survivalcoding.ifkakao.presentation.detail.adapter.SpeakerListAdapter
import com.survivalcoding.ifkakao.presentation.detail.adapter.TagListAdapter
import com.survivalcoding.ifkakao.presentation.util.SessionListAdapter

class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val relatedSessionsAdapter by lazy {
        SessionListAdapter(
            onClickListener = {
                viewModel.nextSession(
                    it,
                    binding.backgroundNestedScrollView.scrollX,
                    binding.backgroundNestedScrollView.scrollY
                )
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

    private val speakerAdapter by lazy { SpeakerListAdapter() }

    private val viewModel by activityViewModels<MainViewModel> {
        MainViewModelFactory((requireActivity().application as App).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.initViewModel(FragmentType.DETAIL)

        binding.rvRelatedSessionsList.adapter = relatedSessionsAdapter
        binding.rvTagList.adapter = tagsAdapter
        binding.rvSpeakerList.adapter = speakerAdapter
        binding.btnMoreSessions.setOnClickListener {
            viewModel.exposeMoreRelatedSessions()
        }

        viewModel.detailUIState.observe(viewLifecycleOwner) { detailUIState ->
            detailUIState.session?.let {
                binding.tvDetailTitle.text = it.title
                binding.tvDetailContent.text = it.content
                binding.tvDetailHashtag.text = it.hashTag
                tagsAdapter.submitList(it.tag)
                speakerAdapter.submitList(it.sessionSpeakers)
                relatedSessionsAdapter.submitList(detailUIState.exposedList)
            }
            binding.btnMoreSessions.isVisible = detailUIState.hasMoreRelatedSessions
        }

        viewModel.scrollPosition.observe(viewLifecycleOwner) {
            binding.backgroundNestedScrollView.smoothScrollTo(it.first, it.second)
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}