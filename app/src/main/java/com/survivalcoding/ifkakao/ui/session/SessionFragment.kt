package com.survivalcoding.ifkakao.ui.session

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.Toast
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

    companion object {
        const val FILTER_DIALOG_REQUEST_KEY = "FILTER_DIALOG_REQUEST_KEY"
        const val FIELDS_KEY = "FIELDS_KEY"
        const val CLASSIFICATIONS_KEY = "CLASSIFICATIONS_KEY"
        const val TECHNICAL_CLASSIFICATIONS_KEY = "TECHNICAL_CLASSIFICATIONS_KEY"
        const val COMPANIES_KEY = "COMPANIES_KEY"
    }

    private var _binding: FragmentSessionBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SessionViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFragmentResultListener(FILTER_DIALOG_REQUEST_KEY) { _, bundle ->
            val filedFilters = bundle.getStringArray(FIELDS_KEY)
            val classificationFilters = bundle.getStringArray(CLASSIFICATIONS_KEY)
            val technicalClassificationFilters = bundle.getStringArray(TECHNICAL_CLASSIFICATIONS_KEY)
            val companyFilter = bundle.getStringArray(COMPANIES_KEY)
            Toast.makeText(requireContext(), "!", Toast.LENGTH_SHORT).show()
        }
    }

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

        binding.daySp.adapter = ArrayAdapter.createFromResource(requireContext(), R.array.spinner_item_array, R.layout.spinner_item_session)

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
                    adapter.submitList(it.sessions)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}