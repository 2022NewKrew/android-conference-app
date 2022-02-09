package com.survivalcoding.ifkakao.presentation.main

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
import com.survivalcoding.ifkakao.databinding.FragmentMainBinding
import com.survivalcoding.ifkakao.presentation.commons.FooterAdapter
import com.survivalcoding.ifkakao.presentation.commons.SessionAdapter
import com.survivalcoding.ifkakao.presentation.detail.DetailFragment
import com.survivalcoding.ifkakao.presentation.sessions.SessionsFragment
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainFragment : Fragment() {
    private val mainViewModel by viewModel<MainViewModel>()
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = binding.recyclerView
        val concatAdapter = ConcatAdapter(
            MainHeaderAdapter {
                moveToNextFragment(SessionsFragment())
            },
            SessionAdapter(onClickSession = { idx ->
                moveToNextFragment(DetailFragment().apply {
                    this.arguments = bundleOf(SELECTED to idx)
                })
            }),
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

        mainViewModel.infos.observe(viewLifecycleOwner) {
            (concatAdapter.adapters[1] as SessionAdapter).submitList(it)
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

    companion object {
        const val SELECTED = "selected"
    }

}