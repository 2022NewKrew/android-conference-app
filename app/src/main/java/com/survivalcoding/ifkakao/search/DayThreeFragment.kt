package com.survivalcoding.ifkakao.search

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.survivalcoding.ifkakao.MainViewModel
import com.survivalcoding.ifkakao.R
import com.survivalcoding.ifkakao.adapter.SessionListAdapter
import com.survivalcoding.ifkakao.compose.SessionFragment
import com.survivalcoding.ifkakao.databinding.FragmentDayThreeBinding
import com.survivalcoding.ifkakao.databinding.FragmentSearchBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class DayThreeFragment : Fragment() {

    private val viewModel by activityViewModels<MainViewModel>()
    private var _binding: FragmentDayThreeBinding? = null
    val binding get() = _binding!!

    private val adapter = SessionListAdapter(
        onClicked = { data ->
            viewModel.saveClickedSession(data)
            parentFragmentManager.commit {
                replace<SessionFragment>(R.id.fragment_container_view)
                addToBackStack(null)
            }
        }
    )


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentDayThreeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.getDaySessions(20211118)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.dayThreeRecyclerview.adapter = adapter

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.daysItems.collect {
                    adapter.submitList(it)
                }
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}