package com.survivalcoding.ifkakao.presentation.detail

import android.content.Context
import android.graphics.Typeface
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.core.view.setPadding
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.survivalcoding.ifkakao.R
import com.survivalcoding.ifkakao.databinding.ItemDetailExplanationBinding
import com.survivalcoding.ifkakao.domain.model.ContentsSpeaker
import com.survivalcoding.ifkakao.domain.model.ProfileInfo
import com.survivalcoding.ifkakao.domain.model.Session

const val KILOBYTE = 1024

class ExplanationViewHolder(
    itemView: View,
    private val isFavorite: Boolean,
    private val onClickFavoriteButton: (Boolean) -> Unit
) : RecyclerView.ViewHolder(itemView) {
    val binding = ItemDetailExplanationBinding.bind(itemView)
    private val ctx: Context = itemView.context
    fun bind(session: Session) {
        val fields: List<String> =
            listOf(session.company!!, session.field) + session.relationList.CLASSIFICATION!!
        binding.SessionInfoLayout.removeAllViews()
        for (str in fields) {
            val text = TextView(ctx)
            text.text = str
            text.setTypeface(null, Typeface.BOLD)
            text.background = ContextCompat.getDrawable(ctx, R.drawable.bg_gray_box)
            text.setPadding(20 / (itemView.resources.displayMetrics.density).toInt())

            binding.SessionInfoLayout.addView(text)
        }

        val title = binding.titleText
        title.text = session.title

        val content = binding.contentText
        content.text = session.content

        val tags = binding.tagText
        tags.text = session.contentTag
        tags.setTextColor(ContextCompat.getColor(ctx, R.color.light_gray))

        val download = binding.downloadLayout
        download.isVisible = !session.linkList?.FILE.isNullOrEmpty()
        if (!session.linkList?.FILE.isNullOrEmpty()) {
            binding.fileSize.text = textOfFileSize(session.linkList!!.FILE!![0].fileSize!!)

            binding.fileName.setOnClickListener {
                //ToDo: Download 구현 및 pdf 파일 오픈
            }
        }

        val favoriteButton = binding.favoriteButton
        favoriteButton.isSelected = isFavorite
        favoriteButton.setOnClickListener {
            favoriteButton.isSelected = !favoriteButton.isSelected
            onClickFavoriteButton(favoriteButton.isSelected)
        }

        val recyclerView = binding.profileRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(ctx)
        val adapter = ProfileAdapter()
        recyclerView.adapter = adapter
        val contentsSpeaker: List<ContentsSpeaker>? = session.contentsSpeakerList
        val profileInfos = mutableListOf<ProfileInfo>()
        if (contentsSpeaker != null) {
            for ((idx, speaker) in contentsSpeaker.withIndex()) {
                profileInfos.add(
                    ProfileInfo(
                        channelLink = speaker.channelLink,
                        company = speaker.company,
                        nameEn = speaker.nameEn,
                        nameKo = speaker.nameKo,
                        occupation = speaker.occupation,
                        url = session.linkList?.SPEAKER_PROFILE?.get(idx)?.url
                    )
                )
            }
        }

        adapter.submitList(profileInfos)
    }

    private fun textOfFileSize(fileSize: Int): String {
        return if (fileSize < KILOBYTE * KILOBYTE) fileSize.div(KILOBYTE).toString() + "KB"
        else fileSize.div(KILOBYTE * KILOBYTE).toString() + "MB"
    }
}