package com.survivalcoding.ifkakao.search

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.AdapterView
import android.widget.ScrollView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.domain.entity.ContentState
import com.example.domain.entity.OrderState
import com.survivalcoding.ifkakao.MainViewModel
import com.survivalcoding.ifkakao.R
import com.survivalcoding.ifkakao.databinding.FragmentSearchBinding
import com.survivalcoding.ifkakao.dialog.TagSelectFragment

class SearchFragment : Fragment() {

    private val viewModel by activityViewModels<MainViewModel>()
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private var contentState = ContentState.title
    private var orderState = OrderState.asc


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Glide.with(this).load(R.drawable.vod_teaser_2021_pc).into(binding.searchViewMainImage)
        } else {
            Glide.with(this).load(R.drawable.vod_teaser_2021_mobile)
                .into(binding.searchViewMainImage)
        }

        //sorting open & close
        binding.sortingLayout.setOnClickListener {
            binding.itemSortLayout.root.visibility =
                when (binding.itemSortLayout.root.visibility) {
                    View.GONE -> View.VISIBLE
                    View.VISIBLE -> View.GONE
                    else -> View.VISIBLE
                }
            //todo use value to know the specific state
            arrowRotation(binding.itemSortLayout.root.visibility)
        }

        // sorting
        binding.itemSortLayout.contentSortGroupView.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.company_sorting -> contentState = ContentState.company
                R.id.title_sorting -> contentState = ContentState.title
                R.id.category_sorting -> contentState = ContentState.category
            }
            viewModel.getSortedDateSession(contentState, orderState)
        }

        binding.itemSortLayout.orderSortGroupView.setOnCheckedChangeListener { _, checkId ->
            when (checkId) {
                R.id.ascending -> orderState = OrderState.asc
                R.id.descending -> orderState = OrderState.desc
            }
            viewModel.getSortedDateSession(contentState, orderState)
        }

        //select tag
        //todo keywords filter viewpager 모두 반영
        binding.filterImgBtn.setOnClickListener {
            TagSelectFragment().show(parentFragmentManager, "tag")
        }


        //viewpager2
        //todo date 값이 없는 것도 있음(애자일)
        binding.viewPager.adapter = FragmentAdapter(requireActivity())
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.spinner.setSelection(position)
                when (position) {
                    0 -> viewModel.date.value = 20211116
                    1 -> viewModel.date.value = 20211117
                    2 -> viewModel.date.value = 20211118
                }
                viewModel.getSortedDateSession(contentState, orderState)
            }
        })

        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                binding.viewPager.setCurrentItem(position, true)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

        binding.scrollTopView.scrollTopImage.setOnClickListener {
            binding.searchNestedScrollView.fullScroll(ScrollView.FOCUS_UP)
        }


    }

    private fun arrowRotation(visibility: Int) {
        val ra = when (visibility) {
            View.GONE -> RotateAnimation(
                180F,
                360F,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f
            )
            else -> {
                RotateAnimation(
                    0F,
                    180F,
                    Animation.RELATIVE_TO_SELF, 0.5f,
                    Animation.RELATIVE_TO_SELF, 0.5f
                )
            }
        }
        ra.duration = 250
        ra.fillAfter = true
        binding.sortingArrow.startAnimation(ra)
    }


}