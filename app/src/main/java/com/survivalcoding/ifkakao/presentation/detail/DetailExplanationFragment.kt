package com.survivalcoding.ifkakao.presentation.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ConcatAdapter
import com.survivalcoding.ifkakao.R
import com.survivalcoding.ifkakao.databinding.FragmentDetailExplanationBinding
import com.survivalcoding.ifkakao.presentation.commons.FooterAdapter
import com.survivalcoding.ifkakao.presentation.commons.SessionAdapter
import com.survivalcoding.ifkakao.presentation.commons.sharedViewModel
import com.survivalcoding.ifkakao.presentation.main.MainFragment.Companion.SELECTED

class DetailExplanationFragment : Fragment() {
    private val detailViewModel: DetailViewModel by sharedViewModel<DetailViewModel, DetailFragment>()

    private var _binding: FragmentDetailExplanationBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailExplanationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = binding.recyclerView
        val concatAdapter = ConcatAdapter(
            ExplanationAdapter(),
            SessionAdapter { idx ->
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(
                        R.id.fragmentContainerView,
                        DetailFragment().apply {
                            this.arguments = bundleOf(SELECTED to idx)
                        }
                    )
                    .addToBackStack(null)
                    .commit()
            },
            FooterAdapter(
                onClickUpButton = {
                    recyclerView.smoothScrollToPosition(0)
                }, onClickSite = {
                    val browserIntent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://if.kakao.com/2020/")
                    )
                    if (browserIntent.resolveActivity(requireActivity().packageManager) != null) {
                        startActivity(browserIntent)
                    }
                }
            )
        )
        recyclerView.adapter = concatAdapter

        detailViewModel.session.observe(viewLifecycleOwner) {
            (concatAdapter.adapters[0] as ExplanationAdapter).updateSession(it)
        }

        detailViewModel.relatedSessions.observe(viewLifecycleOwner) {
            (concatAdapter.adapters[1] as SessionAdapter).submitList(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
