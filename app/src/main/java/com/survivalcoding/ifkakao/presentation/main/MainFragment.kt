package com.survivalcoding.ifkakao.presentation.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.ConcatAdapter
import com.survivalcoding.ifkakao.data.datasource.local.MockLocalDataSource
import com.survivalcoding.ifkakao.data.datasource.remote.MockRemoteDataSource
import com.survivalcoding.ifkakao.data.repository.SessionRepositoryImpl
import com.survivalcoding.ifkakao.databinding.FragmentMainBinding
import com.survivalcoding.ifkakao.presentation.util.SessionAdapter

class MainFragment : Fragment() {
    private val viewModel by activityViewModels<MainViewModel> {
        MainViewModelFactory(
            application = requireActivity().application,
            repository = //(requireActivity().application as App).sessionRepository
            SessionRepositoryImpl(
                MockRemoteDataSource(),
                //SessionRemoteDataSource(RetrofitClient.apiService),
                MockLocalDataSource(),
                /*
                SessionLocalDataSource(
                    Room.databaseBuilder(
                        this,
                        IfKakaoDatabase::class.java,
                        "database"
                    ).build().likeDao()
                )*/
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
        val concatAdapter = ConcatAdapter(
            HeaderAdapter {
                //Todo: Session 창 이동
            },
            SessionAdapter(),
            FooterAdapter {
                //Todo: 위로 올라가기
            })
        recyclerView.adapter = concatAdapter

        viewModel.infos.observe(this) {
            (concatAdapter.adapters[1] as SessionAdapter).submitList(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}