package com.survivalcoding.ifkakao.presentation.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.survivalcoding.ifkakao.App
import com.survivalcoding.ifkakao.R
import com.survivalcoding.ifkakao.databinding.FragmentDetailBinding
import com.survivalcoding.ifkakao.presentation.FragmentInformation
import com.survivalcoding.ifkakao.presentation.detail.adapter.SpeakerListAdapter
import com.survivalcoding.ifkakao.presentation.detail.adapter.TagListAdapter
import com.survivalcoding.ifkakao.presentation.util.SessionListAdapter

class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val relatedSessionsAdapter by lazy {
        SessionListAdapter(
            onClickListener = {
                with((requireActivity().application as App).fragmentStack) {
                    val current = pop()
                    push(current.copy(relatedSessionsCount = viewModel.getSize()))
                    push(FragmentInformation(currentSession = it))
                }
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

    private val viewModel by viewModels<DetailViewModel> {
        DetailViewModelFactory((requireActivity().application as App).allSessions)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) =
        with((requireActivity().application as App).fragmentStack) {
            super.onViewCreated(view, savedInstanceState)

            viewModel.onEvent(DetailEvent.LoadingData(peek()))

            Glide.with(requireContext())
                .load(viewModel.getVideoThumbnailUrl())
                .transition(DrawableTransitionOptions.withCrossFade())
                .fitCenter()
                .into(binding.ivDetailVideoThumbnail)

            binding.rvRelatedSessionsList.adapter = relatedSessionsAdapter
            binding.rvTagList.adapter = tagsAdapter
            binding.rvSpeakerList.adapter = speakerAdapter
            binding.btnMoreSessions.setOnClickListener {
                viewModel.onEvent(DetailEvent.LoadMoreSessions)
            }

            viewModel.uiState.observe(viewLifecycleOwner) { uiState ->
                uiState.session?.let {
                    binding.tvDetailTitle.text = it.title
                    binding.tvDetailContent.text = it.content
                    binding.tvDetailHashtag.text = it.hashTag
                    binding.tvDetailVideoTitle.text = it.title
                    binding.tvDetailVideoLength.text = it.video.videoLength
                    tagsAdapter.submitList(it.tag)
                    speakerAdapter.submitList(it.sessionSpeakers)
                    relatedSessionsAdapter.submitList(uiState.exposedList)
                }
                binding.btnMoreSessions.isVisible = uiState.hasMoreRelatedSessions
            }
        }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}