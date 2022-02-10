package com.survivalcoding.ifkakao.presentation.common

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class CommonAdapter : ListAdapter<CommonBinder, CommonViewHolder>(CommonDiffUtilItemCallback()) {

    private var recyclerView: RecyclerView? = null

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        this.recyclerView = recyclerView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommonViewHolder {
        return when (viewType) {
            AssociatedSessionTitleBinder.ID -> AssociatedSessionTitleViewHolder.from(parent)
            BadgeBinder.ID -> BadgeViewHolder.from(parent)
            CategoryListBinder.ID -> CategoryListViewHolder.from(parent)
            CommonListBinder.ID -> CommonListViewHolder.from(parent)
            DrawerTitleBinder.ID -> DrawerTitleViewHolder.from(parent)
            DrawerSubtitleBinder.ID -> DrawerSubtitleViewHolder.from(parent)
            DrawerSortRadioGroupBinder.ID -> DrawerSortRadioGroupViewHolder.from(parent)
            DrawerEditTextBinder.ID -> DrawerEditTextViewHolder.from(parent)
            KeywordToggleBinder.ID -> KeywordToggleViewHolder.from(parent)
            KeywordToggleListBinder.ID -> KeywordToggleListViewHolder.from(parent)
            SessionListItemBinder.ID -> SessionListItemViewHolder.from(parent)
            SessionTitleBinder.ID -> SessionTitleViewHolder.from(parent)
            SessionContentBinder.ID -> SessionContentViewHolder.from(parent)
            SessionTagsBinder.ID -> SessionTagsViewHolder.from(parent)
            SessionVideoBinder.ID -> SessionVideoViewHolder.from(parent)
            SessionButtonBinder.ID -> SessionButtonViewHolder.from(parent)
            SessionLinksBinder.ID -> SessionLinksViewHolder.from(parent)
            SessionFileBinder.ID -> SessionFileViewHolder.from(parent)
            SpeakerBinder.ID -> SpeakerViewHolder.from(parent)
            else -> FooterViewHolder.from(parent, recyclerView)
        }
    }

    override fun onBindViewHolder(holder: CommonViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemViewType(position: Int): Int {
        return currentList[position].id
    }
}