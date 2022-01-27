package com.survivalcoding.ifkakao.presentation.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.survivalcoding.ifkakao.App
import com.survivalcoding.ifkakao.R
import com.survivalcoding.ifkakao.databinding.FragmentDetailBinding
import com.survivalcoding.ifkakao.presentation.FragmentInformation
import com.survivalcoding.ifkakao.presentation.detail.adapter.SpeakerListAdapter
import com.survivalcoding.ifkakao.presentation.detail.adapter.TagListAdapter
import com.survivalcoding.ifkakao.presentation.util.SessionListAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

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

            collect()
        }

    private fun collect() {
        repeatOnStart {
            viewModel.uiState.collectLatest {
                binding.tvDetailTitle.text = it.session.title
                binding.tvDetailContent.text = it.session.content
                binding.tvDetailHashtag.text = it.session.hashTag
                binding.tvDetailVideoTitle.text = it.session.title
                binding.tvDetailVideoLength.text = it.session.video.videoLength
                tagsAdapter.submitList(it.session.tag)
                speakerAdapter.submitList(it.session.sessionSpeakers)
                relatedSessionsAdapter.submitList(it.exposedList)
                binding.btnMoreSessions.isVisible = it.hasMoreRelatedSessions
            }
        }
    }

    private fun repeatOnStart(block: suspend CoroutineScope.() -> Unit) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED, block)
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}