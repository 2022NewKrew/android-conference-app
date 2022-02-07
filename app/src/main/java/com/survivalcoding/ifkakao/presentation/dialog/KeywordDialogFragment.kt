package com.survivalcoding.ifkakao.presentation.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import com.survivalcoding.ifkakao.databinding.FragmentKeywordDialogBinding
import com.survivalcoding.ifkakao.presentation.session.adapter.KeywordAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class KeywordDialogFragment(
    private val isChanged: MutableLiveData<Boolean>,
) : DialogFragment() {

    private var _binding: FragmentKeywordDialogBinding? = null
    private val binding get() = _binding!!

    private val viewModel: KeywordDialogViewModel by viewModels()

    private val keywordAdapters = listOf(
        KeywordAdapter { viewModel.onEvent(KeywordDialogEvent.ToggleField(it)) },
        KeywordAdapter { viewModel.onEvent(KeywordDialogEvent.ToggleClass(it)) },
        KeywordAdapter { viewModel.onEvent(KeywordDialogEvent.ToggleTech(it)) },
        KeywordAdapter { viewModel.onEvent(KeywordDialogEvent.ToggleCompany(it)) },
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isCancelable = true
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentKeywordDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.exitButton.setOnClickListener { dismiss() }
        binding.initializeButton.setOnClickListener { viewModel.onEvent(KeywordDialogEvent.Init) }
        binding.applyButton.setOnClickListener {
            viewModel.onEvent(KeywordDialogEvent.Save)
            isChanged.value = true
            dismiss()
        }

        binding.apply {
            fieldAdapter = keywordAdapters[0]
            classAdapter = keywordAdapters[1]
            techAdapter = keywordAdapters[2]
            companyAdapter = keywordAdapters[3]
        }

        viewModel.keywords.observe(viewLifecycleOwner) {
            keywordAdapters[0].submitList(it.fieldList)
            keywordAdapters[1].submitList(it.classificationList)
            keywordAdapters[2].submitList(it.techClassificationList)
            keywordAdapters[3].submitList(it.companyList)
        }
    }
}