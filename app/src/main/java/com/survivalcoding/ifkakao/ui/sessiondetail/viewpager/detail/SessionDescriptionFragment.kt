package com.survivalcoding.ifkakao.ui.sessiondetail.viewpager.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.google.android.flexbox.FlexboxLayout
import com.survivalcoding.ifkakao.R
import com.survivalcoding.ifkakao.databinding.FragmentSessionDescriptionBinding
import com.survivalcoding.ifkakao.databinding.ListItemSpeakerBinding
import com.survivalcoding.ifkakao.domain.model.Data
import com.survivalcoding.ifkakao.ui.session.SessionFilter
import com.survivalcoding.ifkakao.ui.sessiondetail.SessionDetailFragmentDirections

class SessionDescriptionFragment : Fragment() {

    companion object {
        const val ARG_KEY_SESSION = "session"
    }

    private var _binding: FragmentSessionDescriptionBinding? = null
    private val binding get() = _binding!!

    private val session by lazy { requireArguments()[ARG_KEY_SESSION] as Data }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSessionDescriptionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        addTagTv(binding.flexboxLayout, session.field, SessionFilter(fieldFilters = mapOf(session.field to true)))
        addTagTv(binding.flexboxLayout, session.companyName, SessionFilter(companyFilters = mapOf(session.companyName to true)))
        session.relationList.CLASSIFICATION.forEach {
            addTagTv(binding.flexboxLayout, it, SessionFilter(classFilters = mapOf(it to true)))
        }
        session.relationList.TECH_CLASSIFICATION.forEach {
            addTagTv(binding.flexboxLayout, it, SessionFilter(techFilters = mapOf(it to true)))
        }

        binding.titleTv.text = session.title
        binding.contentTv.text = session.content
        binding.tagsTv.text = session.contentTag

        session.contentsSpeakerList.forEachIndexed { index, contentsSpeaker ->
            binding.speakLl.apply {
                val speakerListItemBindng =
                    ListItemSpeakerBinding.inflate(layoutInflater, this, false)
                Glide.with(this).load(session.linkList.SPEAKER_PROFILE[index].url)
                    .into(speakerListItemBindng.profileSiv)
                speakerListItemBindng.nameTv.setTextOrGone("${contentsSpeaker.nameKo} ${contentsSpeaker.nameEn}")
                speakerListItemBindng.companyTv.setTextOrGone(contentsSpeaker.company)
                speakerListItemBindng.occupationTv.setTextOrGone(contentsSpeaker.occupation)
                addView(speakerListItemBindng.root)
            }
        }
    }

    private fun addTagTv(flexboxLayout: FlexboxLayout, tag: String, sessionFilter: SessionFilter) {
        flexboxLayout.apply {
            val tagTv = layoutInflater.inflate(R.layout.textview_tag, this, false) as TextView
            tagTv.text = tag
            tagTv.setOnClickListener {
                findNavController().navigate(
                    SessionDetailFragmentDirections.actionSessionDetailFragmentToSessionSearchFragment(
                        sessionFilter
                    )
                )
            }
            addView(tagTv)
        }
    }

    private fun TextView.setTextOrGone(text: String?) {
        if (text == null || text.isEmpty()) {
            this.isVisible = false
        } else {
            this.text = text
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}