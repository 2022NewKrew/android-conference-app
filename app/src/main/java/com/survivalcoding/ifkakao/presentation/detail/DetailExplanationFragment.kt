package com.survivalcoding.ifkakao.presentation.detail

import android.content.Intent
import android.content.res.Configuration.ORIENTATION_PORTRAIT
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ConcatAdapter
import com.survivalcoding.ifkakao.R
import com.survivalcoding.ifkakao.databinding.FragmentDetailExplanationBinding
import com.survivalcoding.ifkakao.presentation.commons.FooterAdapter
import com.survivalcoding.ifkakao.presentation.commons.SessionAdapter
import com.survivalcoding.ifkakao.presentation.main.MainFragment.Companion.SELECTED
import com.survivalcoding.ifkakao.presentation.sessions.SessionsFragment
import com.survivalcoding.ifkakao.sharedViewModel

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
        recyclerView.isNestedScrollingEnabled =
            resources.configuration.orientation == ORIENTATION_PORTRAIT

        val concatAdapter = ConcatAdapter(
            ExplanationAdapter(onClickFavoriteButton = {
                detailViewModel.setLike()
                //Toast.makeText(requireContext(), "isFavorite: " + it, Toast.LENGTH_SHORT).show()
            }, onClickSessionButton = {
                moveToNextFragment(SessionsFragment())
            }),
            SessionAdapter { idx ->
                moveToNextFragment(DetailFragment().apply {
                    this.arguments = bundleOf(SELECTED to idx)
                })
            },
            FooterAdapter(
                onClickUpButton = {
                    if (resources.configuration.orientation == ORIENTATION_PORTRAIT)
                        recyclerView.smoothScrollToPosition(0)
                    else requireParentFragment().requireView()
                        .findViewById<NestedScrollView>(R.id.scrollView).smoothScrollTo(0, 0)
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

        detailViewModel.isLiking.observe(viewLifecycleOwner) {
            (concatAdapter.adapters[0] as ExplanationAdapter).updateLiking(it)
        }
    }

    private fun moveToNextFragment(fragment: Fragment) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(
                R.id.fragmentContainerView,
                fragment
            )
            .addToBackStack(null)
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
