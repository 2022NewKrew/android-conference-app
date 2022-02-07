package com.survivalcoding.ifkakao.ui.session.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.CheckBox
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import com.google.android.flexbox.FlexboxLayout
import com.survivalcoding.ifkakao.R
import com.survivalcoding.ifkakao.databinding.CheckboxFilterBinding
import com.survivalcoding.ifkakao.databinding.FragmentFilterDialogBinding
import com.survivalcoding.ifkakao.ui.session.SessionFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FilterDialogFragment: DialogFragment() {
    private var _binding: FragmentFilterDialogBinding? = null
    private val binding get() = _binding!!

    private val viewModel: FilterDialogViewModel by viewModels()

    private var checkCount = 0

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

        val fieldCheckBoxes = initCheckBoxes(viewModel.getFilterFieldItems(), binding.fieldFbl)
        val classificationCheckBoxes = initCheckBoxes(viewModel.getFilterClassificationItems(), binding.classificationFlb)
        val technicalClassificationCheckBoxes = initCheckBoxes(viewModel.getFilterTechnicalClassificationItems(), binding.techClassificationFlb)
        val companyCheckBoxes = initCheckBoxes(viewModel.getFilterCompanyItems(), binding.companyFlb)
        val allCheckBoxes = fieldCheckBoxes + classificationCheckBoxes + technicalClassificationCheckBoxes + companyCheckBoxes

        binding.resetBt.setOnClickListener {
            allCheckBoxes.forEach { it.isChecked = false }
        }

        binding.applyBt.setOnClickListener {
            val bundle = Bundle().apply {
                putStringArray(SessionFragment.FIELDS_KEY, fieldCheckBoxes.filter { it.isChecked }.map { it.text.toString() }.toTypedArray())
                putStringArray(SessionFragment.CLASSIFICATIONS_KEY, classificationCheckBoxes.filter { it.isChecked }.map { it.text.toString() }.toTypedArray())
                putStringArray(SessionFragment.TECHNICAL_CLASSIFICATIONS_KEY, technicalClassificationCheckBoxes.filter { it.isChecked }.map { it.text.toString() }.toTypedArray())
                putStringArray(SessionFragment.COMPANIES_KEY, companyCheckBoxes.filter { it.isChecked }.map { it.text.toString() }.toTypedArray())
            }
            setFragmentResult(SessionFragment.FILTER_DIALOG_REQUEST_KEY, bundle)
            dismiss()
        }
    }

    private fun initCheckBoxes(items: List<String>, flexboxLayout: FlexboxLayout): List<CheckBox> {
        return items.map {
            CheckboxFilterBinding.inflate(layoutInflater, flexboxLayout, true).run {
                checkbox.apply {
                    text = it
                    setOnCheckedChangeListener { _, isChecked ->
                        checkCount += if(isChecked) 1 else -1
                        val predicate = checkCount > 0
                        binding.resetBt.text = if(predicate) "초기화" else "초기화($checkCount)"
                        binding.resetBt.setBackgroundColor(ContextCompat.getColor(requireContext(), if(predicate) R.color.darkGrey else R.color.grey))
                        binding.resetBt.setTextColor(ContextCompat.getColor(requireContext(), if(predicate) R.color.lightGrey else R.color.white))
                    }
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