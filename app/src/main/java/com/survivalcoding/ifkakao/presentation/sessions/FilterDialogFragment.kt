package com.survivalcoding.ifkakao.presentation.sessions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.survivalcoding.ifkakao.databinding.DialogFilterBinding
import com.survivalcoding.ifkakao.presentation.sessions.adapter.FilterListAdapter

class FilterDialogFragment : DialogFragment() {
    private var _binding: DialogFilterBinding? = null
    private val binding get() = _binding!!
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
    private val serviceBusinessAdapter =
        FilterListAdapter().apply { submitList(keywordServiceBusiness) }
    private val techAdapter =
        FilterListAdapter().apply { submitList(keywordTech) }
    private val companyAdapter =
        FilterListAdapter().apply { submitList(keywordCompany) }

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
        binding.filterRvServiceBusiness.adapter = serviceBusinessAdapter
        binding.filterRvTech.adapter = techAdapter
        binding.filterRvCompany.adapter = companyAdapter
    }

    override fun onResume() {
        super.onResume()
        // full Screen code
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )

        binding.filterIbClose.setOnClickListener { dismiss() }
        binding.filterBtnApply.setOnClickListener { dismiss() }
    }
}