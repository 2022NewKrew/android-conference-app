package com.survivalcoding.ifkakao.ui.sessionsearch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.survivalcoding.ifkakao.databinding.FragmentSessionSearchBinding
import com.survivalcoding.ifkakao.ui.main.adapter.SessionAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SessionSearchFragment: Fragment() {

    private var _binding: FragmentSessionSearchBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SessionSearchViewModel by viewModels()

    private val args: SessionSearchFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSessionSearchBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.searchHeaderTv.text = args.sessionFilter.toString()

        val adapter = SessionAdapter {
            findNavController().navigate(SessionSearchFragmentDirections.actionSessionSearchFragmentToSessionDetailFragment(it))
        }
        binding.sessionRv.adapter = adapter

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.sessions.collectLatest {
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