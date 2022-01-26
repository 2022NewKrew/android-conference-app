package com.survivalcoding.ifkakao.presentation.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.ConcatAdapter
import com.survivalcoding.ifkakao.App
import com.survivalcoding.ifkakao.R
import com.survivalcoding.ifkakao.databinding.FragmentMainBinding
import com.survivalcoding.ifkakao.presentation.detail.DetailFragment
import com.survivalcoding.ifkakao.presentation.util.SessionAdapter


class MainFragment : Fragment() {
    private val viewModel by activityViewModels<MainViewModel> {
        MainViewModelFactory(
            application = requireActivity().application,
            repository = (requireActivity().application as App).sessionRepository
        )
    }
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
            HeaderAdapter {
                //Todo: Session 창 이동
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

        viewModel.infos.observe(this) {
            (concatAdapter.adapters[1] as SessionAdapter).submitList(it)
        }
    }

    private fun moveToNextFragment(fragment: Fragment) {
        parentFragmentManager.beginTransaction()
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