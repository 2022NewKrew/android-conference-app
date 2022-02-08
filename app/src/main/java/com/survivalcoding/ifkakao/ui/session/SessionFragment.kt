package com.survivalcoding.ifkakao.ui.session

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.bumptech.glide.Glide
import com.survivalcoding.ifkakao.R
import com.survivalcoding.ifkakao.databinding.FragmentSessionBinding
import com.survivalcoding.ifkakao.ui.main.adapter.SessionAdapter
import com.survivalcoding.ifkakao.ui.session.dialog.FilterDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SessionFragment : Fragment() {

    private var _binding: FragmentSessionBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SessionViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSessionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Glide.with(this).load(R.drawable.vod_teaser_2021_mobile).into(binding.sessionIv)

        binding.daySp.adapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.spinner_item_array,
            R.layout.spinner_item_session
        )
        binding.daySp.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                viewModel.applyDayFilter(position + 1)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

        binding.filterBt.setOnClickListener {
            FilterDialogFragment().show(parentFragmentManager, null)
        }

        val adapter = SessionAdapter {
            findNavController().navigate(
                SessionFragmentDirections.actionSessionFragmentToSessionDetailFragment(
                    it
                )
            )
        }
        binding.sessionRv.adapter = adapter
        val decoration = DividerItemDecoration(requireContext(), LinearLayout.VERTICAL)
        binding.sessionRv.addItemDecoration(decoration)

        binding.footerIcl.scrollToTopIbt.setOnClickListener {
            binding.nestedScrollView.smoothScrollTo(0, 0)
            binding.appbarLayout.setExpanded(true)
        }

        viewModel.getSessions()

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collectLatest {
                    adapter.submitList(it.sessionFilter.filter(it.sessions))
                    binding.filterBt.text =
                        if (it.sessionFilter.getFilterCount() > 0) {
                            it.sessionFilter.getFilterCount().toString()
                        } else {
                            ""
                        }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}