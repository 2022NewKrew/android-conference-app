package com.survivalcoding.ifkakao.presentation.sessions.viewpager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.fragment.app.viewModels
import com.survivalcoding.ifkakao.R
import com.survivalcoding.ifkakao.databinding.FragmentSessionsDayBinding
import com.survivalcoding.ifkakao.domain.model.DayType
import com.survivalcoding.ifkakao.domain.model.Session
import com.survivalcoding.ifkakao.presentation.adapter.SessionListAdapter
import com.survivalcoding.ifkakao.presentation.detail.DetailFragment
import com.survivalcoding.ifkakao.presentation.sessions.SessionsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SessionsDayFragment : Fragment() {
    private var _binding: FragmentSessionsDayBinding? = null
    private val binding get() = _binding!!
    private val day by lazy { requireArguments()["day"] as DayType }
    private val viewModel: SessionsDayViewModel by viewModels()
    private val sessionViewModel: SessionsViewModel by viewModels(ownerProducer = { requireParentFragment() })
    private val adapter by lazy {
        SessionListAdapter { session -> moveToDetail(session) }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSessionsDayBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchSessions()

        // recyclerView 설정
        binding.sessionsRecyclerView.adapter = adapter

        viewModel.sessions.observe(this) { sessions ->
            adapter.submitList(sessions)
        }

        // 필터 변경 시 세션 조회
        sessionViewModel.isFilterChanged.observe(this) {
            if (it) searchSessions()
        }
    }

    private fun searchSessions() {
        viewModel.getSessionsByDay(
            day,
            sessionViewModel.selectedField.value ?: mutableListOf(),
            sessionViewModel.selectedKeyword.value ?: mutableListOf(),
            sessionViewModel.selectedCompany.value ?: mutableListOf()
        )
    }

    override fun onResume() {
        super.onResume()
        binding.root.requestLayout()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun moveToDetail(session: Session?) {
        parentFragment?.parentFragmentManager?.commit {
            replace<DetailFragment>(
                R.id.fragment_container_view,
                args = bundleOf("sessionId" to session?.idx),
            )
            setReorderingAllowed(true)
            addToBackStack(null)
        }
    }
}