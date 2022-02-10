package com.survivalcoding.ifkakao.presentation.sessions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.survivalcoding.ifkakao.databinding.DialogFilterBinding
import com.survivalcoding.ifkakao.presentation.sessions.adapter.FilterListAdapter

class FilterDialogFragment : DialogFragment() {
    private var _binding: DialogFilterBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SessionsViewModel by viewModels(ownerProducer = { requireParentFragment() })
    private val keywordField = listOf("서비스", "비즈니스", "기술")
    private val keywordServiceBusiness = listOf(
        "플랫폼", "커머스", "B2B", "구독", "광고&마케팅", "핀테크", "디지털자산", "콘텐츠", "크리에이터", "ESG", "파트너성장", "소셜임팩트"
    )
    private val keywordTech = listOf(
        "백엔드",
        "머신러닝/AI",
        "데이터",
        "클라우드",
        "인프라/DevOps",
        "블록체인",
        "지식그래프",
        "모바일",
        "Android",
        "iOS",
        "웹/프론트엔드",
        "IoT",
        "오픈소스",
        "개발문화",
        "기타"
    )
    private val keywordCompany = listOf(
        "카카오",
        "카카오게임즈",
        "카카오모빌리티",
        "카카오뱅크",
        "카카오브레인",
        "카카오스타일",
        "카카오엔터테인먼트",
        "카카오엔터프라이즈",
        "카카오임팩트",
        "카카오페이",
        "카카오커머스",
        "그라운드X"
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogFilterBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // full Screen code
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )

        binding.filterRvField.adapter =
            FilterListAdapter(viewModel.tmpSelectedField.value ?: mutableListOf()) {
                viewModel.selectField(it)
            }.apply { submitList(keywordField) }
        binding.filterRvServiceBusiness.adapter =
            FilterListAdapter(viewModel.tmpSelectedKeyword.value ?: mutableListOf()) {
                viewModel.selectKeyword(it)
            }.apply { submitList(keywordServiceBusiness) }
        binding.filterRvTech.adapter =
            FilterListAdapter(viewModel.tmpSelectedKeyword.value ?: mutableListOf()) {
                viewModel.selectKeyword(it)
            }.apply { submitList(keywordTech) }
        binding.filterRvCompany.adapter =
            FilterListAdapter(viewModel.tmpSelectedCompany.value ?: mutableListOf()) {
                viewModel.selectCompany(it)
            }.apply { submitList(keywordCompany) }

        binding.filterIbClose.setOnClickListener { dismiss() }
        binding.filterBtnClear.setOnClickListener {
            viewModel.resetFilter()
            dismiss()
        }
        binding.filterBtnApply.setOnClickListener {
            viewModel.setField()
            viewModel.setKeyword()
            viewModel.setCompany()
            viewModel.calculateCount()
            dismiss()
        }
    }
}