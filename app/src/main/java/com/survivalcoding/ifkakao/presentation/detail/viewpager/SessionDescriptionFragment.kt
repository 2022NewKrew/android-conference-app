package com.survivalcoding.ifkakao.presentation.detail.viewpager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.survivalcoding.ifkakao.R
import com.survivalcoding.ifkakao.databinding.FragmentSessionDescriptionBinding
import com.survivalcoding.ifkakao.domain.model.Session
import com.survivalcoding.ifkakao.presentation.detail.adapter.ClassificationListAdapter
import com.survivalcoding.ifkakao.presentation.detail.adapter.SpeakerListAdapter
import com.survivalcoding.ifkakao.presentation.sessions.SessionsFragment

class SessionDescriptionFragment : Fragment() {
    private var _binding: FragmentSessionDescriptionBinding? = null
    private val binding get() = _binding!!
    private val session by lazy { requireArguments()["session"] as Session }
    private val classificationAdapter by lazy { ClassificationListAdapter() }
    private val speakerAdapter by lazy { SpeakerListAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSessionDescriptionBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.sessionDescriptionTvTitle.text = session.title
        binding.sessionDescriptionTvContent.text = session.content
        binding.sessionDescriptionTvContentTag.text = session.contentTag

        // classification recyclerView 설정
        val classifications = mutableListOf<String>()
        classifications.add(session.field)
        classifications.add(session.company)
        classifications.addAll(session.relationList.CLASSIFICATION)

        binding.sessionDescriptionRvClassification.adapter = classificationAdapter
        classificationAdapter.submitList(classifications)

        // speaker recyclerView 설정
        binding.sessionDescriptionRvSpeaker.adapter = speakerAdapter
        speakerAdapter.submitList(session.contentsSpeakerList)
        session.linkList.SPEAKER_PROFILE

        binding.sessionDescriptionBtnList.setOnClickListener { moveToSessions() }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun moveToSessions() {
        requireParentFragment().parentFragmentManager.commit {
            replace<SessionsFragment>(R.id.fragment_container_view)
            setReorderingAllowed(true)
            addToBackStack(null)
        }
    }
}