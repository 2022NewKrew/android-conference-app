package com.survivalcoding.ifkakao.ui.sessiondetail.viewpager.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.survivalcoding.ifkakao.R
import com.survivalcoding.ifkakao.databinding.FragmentSessionDescriptionBinding
import com.survivalcoding.ifkakao.databinding.ListItemSpeakerBinding
import com.survivalcoding.ifkakao.domain.model.Data

class SessionDescriptionFragment: Fragment() {

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
        val tags = listOf(session.field, session.companyName) + session.relationList.CLASSIFICATION
        tags.forEach { tag ->
            binding.flexboxLayout.apply {
                val tagTv = layoutInflater.inflate(R.layout.textview_tag, this, false) as TextView
                tagTv.text = tag
                addView(tagTv)
            }
        }

        binding.titleTv.text = session.title
        binding.contentTv.text = session.content
        binding.tagsTv.text = session.contentTag

       session.contentsSpeakerList.forEachIndexed { index, contentsSpeaker ->
           binding.speakLl.apply {
               val speakerListItemBindng = ListItemSpeakerBinding.inflate(layoutInflater, this, false)
               Glide.with(this).load(session.linkList.SPEAKER_PROFILE[index].url).into(speakerListItemBindng.profileSiv)
               speakerListItemBindng.nameTv.setTextOrGone("${contentsSpeaker.nameKo} ${contentsSpeaker.nameEn}")
               speakerListItemBindng.companyTv.setTextOrGone(contentsSpeaker.company)
               speakerListItemBindng.occupationTv.setTextOrGone(contentsSpeaker.occupation)
               addView(speakerListItemBindng.root)
           }
       }
    }

    private fun TextView.setTextOrGone(text: String?) {
        if(text == null || text.isEmpty()) {
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