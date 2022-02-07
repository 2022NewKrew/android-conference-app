package com.survivalcoding.ifkakao.presentation.sessions

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
import com.survivalcoding.ifkakao.databinding.FragmentSessionsBinding
import com.survivalcoding.ifkakao.presentation.commons.FooterAdapter
import com.survivalcoding.ifkakao.presentation.commons.SessionAdapter
import com.survivalcoding.ifkakao.presentation.detail.DetailFragment
import com.survivalcoding.ifkakao.presentation.main.MainFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class SessionsFragment : Fragment() {
    private val sessionsViewModel by viewModel<SessionsViewModel>()
    private var _binding: FragmentSessionsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSessionsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = binding.recyclerView
        val adapter = ConcatAdapter(
            SessionsHeaderAdapter(onClickTab = { idx ->
                sessionsViewModel.updateSessionsByDay(idx + 1)
            }, onClickFilter = {

            }),
            SessionAdapter(onClickSession = { idx ->
                moveToNextFragment(DetailFragment().apply {
                    this.arguments = bundleOf(MainFragment.SELECTED to idx)
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

        recyclerView.adapter = adapter

        sessionsViewModel.sessions.observe(viewLifecycleOwner) {
            (adapter.adapters[1] as SessionAdapter).submitList(it)
        }

        sessionsViewModel.day.observe(viewLifecycleOwner) {
            (adapter.adapters[0] as SessionsHeaderAdapter).setTabSelection(it - 1)
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

}