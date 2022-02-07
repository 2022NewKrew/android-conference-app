package com.survivalcoding.ifkakao.presentation.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.survivalcoding.ifkakao.R
import com.survivalcoding.ifkakao.domain.model.ProfileInfo

class ProfileAdapter : ListAdapter<ProfileInfo, ProfileViewHolder>(ProfileDiffItemCallback) {

    object ProfileDiffItemCallback : DiffUtil.ItemCallback<ProfileInfo>() {
        override fun areItemsTheSame(oldItem: ProfileInfo, newItem: ProfileInfo): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

        override fun areContentsTheSame(oldItem: ProfileInfo, newItem: ProfileInfo): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_detail_profile, parent, false)
        return ProfileViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
