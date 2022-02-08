package com.survivalcoding.ifkakao.ui.session.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.CheckBox
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.flexbox.FlexboxLayout
import com.survivalcoding.ifkakao.R
import com.survivalcoding.ifkakao.databinding.CheckboxFilterBinding
import com.survivalcoding.ifkakao.databinding.FragmentFilterDialogBinding
import com.survivalcoding.ifkakao.ui.session.SessionFilter
import com.survivalcoding.ifkakao.ui.session.SessionFragment
import com.survivalcoding.ifkakao.ui.session.SessionViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FilterDialogFragment: DialogFragment() {
    private var _binding: FragmentFilterDialogBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SessionViewModel by activityViewModels()

    private var checkCount = 0
    private var lastSessionFilter: SessionFilter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFilterDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.closeIbt.setOnClickListener { dismiss() }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collectLatest { uiState ->
                    if(lastSessionFilter != uiState.sessionFilter) {
                        val fieldCbs = initCheckBoxes(uiState.sessionFilter.fieldFilters, binding.fieldFbl)
                        val classCbs = initCheckBoxes(uiState.sessionFilter.classFilters, binding.classFlb)
                        val techCbs = initCheckBoxes(uiState.sessionFilter.techFilters, binding.techFlb)
                        val companyCbs = initCheckBoxes(uiState.sessionFilter.companyFilters, binding.companyFlb)
                        val allCbs = fieldCbs + classCbs + techCbs + companyCbs
                        binding.resetBt.setOnClickListener {
                            allCbs.forEach { it.isChecked = false }
                        }
                        binding.applyBt.setOnClickListener {
                            viewModel.applyFilter(
                                fieldCbs.associate { it.text.toString() to it.isChecked },
                                classCbs.associate { it.text.toString() to it.isChecked },
                                techCbs.associate { it.text.toString() to it.isChecked },
                                companyCbs.associate { it.text.toString() to it.isChecked },
                            )
                            dismiss()
                        }
                        lastSessionFilter = uiState.sessionFilter
                    }
                }
            }
        }
    }

    private fun initCheckBoxes(filter: Map<String, Boolean>, flexboxLayout: FlexboxLayout): List<CheckBox> {
        flexboxLayout.removeAllViews()
        return filter.map {
            CheckboxFilterBinding.inflate(layoutInflater, flexboxLayout, true).run {
                checkbox.apply {
                    text = it.key
                    setOnCheckedChangeListener { _, isChecked ->
                        checkCount += if(isChecked) 1 else -1
                        val predicate = checkCount > 0
                        binding.resetBt.text = if(predicate) "초기화($checkCount)" else "초기화"
                        binding.resetBt.setBackgroundColor(ContextCompat.getColor(requireContext(), if(predicate) R.color.grey else R.color.darkGrey))
                        binding.resetBt.setTextColor(ContextCompat.getColor(requireContext(), if(predicate) R.color.white else R.color.lightGrey))
                    }
                    isChecked = it.value
                }
            }
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        return dialog
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}