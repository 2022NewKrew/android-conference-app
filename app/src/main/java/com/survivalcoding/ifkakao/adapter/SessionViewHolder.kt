package com.survivalcoding.ifkakao.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnAttach
import androidx.core.view.doOnDetach
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.asLiveData
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.entity.Data
import com.survivalcoding.ifkakao.MainViewModel
import com.survivalcoding.ifkakao.R
import com.survivalcoding.ifkakao.databinding.ItemSessionBinding
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.collect

class SessionViewHolder(
    private val binding: ItemSessionBinding,
    private val viewModel: MainViewModel?
) :
    RecyclerView.ViewHolder(binding.root) {

    private var lifecycleOwner: LifecycleOwner? = null
    private var isLogin = false
    private var testItem: Data? = null

    init {
        itemView.doOnAttach {
            lifecycleOwner = itemView.findViewTreeLifecycleOwner()

            lifecycleOwner?.let { holderLifeCycle ->
                viewModel?.isLogin?.asLiveData()?.observe(holderLifeCycle) {
                    isLogin = it
                    if(isLogin) {
                        viewModel.likedList.asLiveData().observe(holderLifeCycle) { likedList ->
                            testItem?.idx?.let { idx ->
                                if (idx in likedList) binding.likeImg.setBackgroundResource(R.drawable.love)
                            }
                        }
                    }
                    else{
                        binding.likeImg.setBackgroundResource(R.drawable.love_border)
                    }
                }
            }
        }
        itemView.doOnDetach {
            lifecycleOwner = null
        }
    }


    fun bind(
        item: Data, onSessionClicked: (Data) -> Unit,
        onLikeClicked: (Int, Boolean) -> Unit,
    ) {
        testItem = item
        binding.sessionTitle.text = item.title
        binding.sessionCompany.text = item.company
        binding.sessionField.text = item.field

/*
        try {
            Glide.with(binding.root).load(item.linkList?.moImage?.first()?.url)
                .into(binding.sessionThumbnail)
        } catch (e: Exception) {
            Log.d("No List", "ThumbNail ${item.idx}")
            binding.sessionThumbnail.setBackgroundResource(R.drawable.ic_baseline_not_interested_24)
        }
*/
        val video = item.linkList?.video?.first()
        if (video?.description != null) {
            binding.sessionVideoTime.text = video.description
        } else {
            binding.sessionVideoTime.visibility = View.GONE
        }
        var isLike = item.isLike
        if (isLike) {
            binding.likeImg.setBackgroundResource(R.drawable.love)
        }

        fun toggleLike() {
            if (isLike) {
                binding.likeImg.setBackgroundResource(R.drawable.love_border)
            } else {
                binding.likeImg.setBackgroundResource(R.drawable.love)
            }
            isLike = !isLike
        }
        binding.likeImg.setOnClickListener {
            toggleLike()
            item.idx?.let {
                onLikeClicked(it, isLike)
            }
        }


        binding.root.setOnClickListener {
            onSessionClicked(item)
        }

    }

    companion object {
        fun builder(parent: ViewGroup, viewModel: MainViewModel? = null): SessionViewHolder =
            SessionViewHolder(
                ItemSessionBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                ),
                viewModel = viewModel
            )
    }
}