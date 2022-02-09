package com.survivalcoding.ifkakao.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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
import com.survivalcoding.ifkakao.databinding.FragmentDayTwoBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class DayTwoFragment : Fragment() {

    private val viewModel by activityViewModels<MainViewModel>()
    private var _binding: FragmentDayTwoBinding? = null
    val binding get() = _binding!!

    private val adapter = SessionListAdapter(
        onSessionClicked = { data ->
            viewModel.saveClickedSession(data)
            parentFragmentManager.commit {
                replace<SessionFragment>(R.id.fragment_container_view)
                addToBackStack(null)
            }
        }, onLikeClicked = { idx, isLike ->
            viewModel.toggleLike(idx, isLike)
        }
    )

    override fun onResume() {
        super.onResume()
        binding.root.requestLayout()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentDayTwoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.dayTwoRecyclerview.adapter = adapter

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