package com.survivalcoding.ifkakao.presentation.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.survivalcoding.ifkakao.data.datasource.local.SessionLocalDataSource
import com.survivalcoding.ifkakao.data.datasource.remote.RetrofitClient
import com.survivalcoding.ifkakao.data.datasource.remote.SessionRemoteDataSource
import com.survivalcoding.ifkakao.data.repository.SessionRepositoryImpl
import com.survivalcoding.ifkakao.databinding.FragmentMainBinding
import com.survivalcoding.ifkakao.presentation.util.SessionAdapter

class MainFragment : Fragment() {
    private val viewModel by activityViewModels<MainViewModel> {
        MainViewModelFactory(
            application = requireActivity().application,
            repository = SessionRepositoryImpl(
                SessionRemoteDataSource(RetrofitClient.apiService),
                SessionLocalDataSource()
            )
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
        val adapter = SessionAdapter()
        recyclerView.adapter = adapter

        viewModel.infos.observe(this) {
            adapter.submitList(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}