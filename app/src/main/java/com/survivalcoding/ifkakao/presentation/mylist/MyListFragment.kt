package com.survivalcoding.ifkakao.presentation.mylist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.survivalcoding.ifkakao.R
import com.survivalcoding.ifkakao.databinding.FragmentMyListBinding
import com.survivalcoding.ifkakao.presentation.commons.SessionAdapter
import com.survivalcoding.ifkakao.presentation.detail.DetailFragment
import com.survivalcoding.ifkakao.presentation.main.MainFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class MyListFragment : Fragment() {
    private val myListViewModel by viewModel<MyListViewModel>()
    private var _binding: FragmentMyListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMyListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = binding.recyclerView
        val adapter = SessionAdapter(onClickSession = { idx ->
            moveToNextFragment(DetailFragment().apply {
                this.arguments = bundleOf(MainFragment.SELECTED to idx)
            })
        })
        recyclerView.adapter = adapter

        myListViewModel.favoriteSessions.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    override fun onResume() {
        super.onResume()
        myListViewModel.updateData()
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