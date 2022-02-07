package com.survivalcoding.ifkakao.presentation.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.survivalcoding.ifkakao.databinding.ItemClassificationBinding

class ClassificationListAdapter :
    ListAdapter<String, ClassificationViewHolder>(ClassificationDiffItemCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClassificationViewHolder {
        return ClassificationViewHolder(
            ItemClassificationBinding.inflate(LayoutInflater.from(parent.context), parent, false),
        )
    }

    override fun onBindViewHolder(holder: ClassificationViewHolder, position: Int) {
        return holder.bind(getItem(position))
    }
}