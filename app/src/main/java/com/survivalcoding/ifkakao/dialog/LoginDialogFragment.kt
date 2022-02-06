package com.survivalcoding.ifkakao.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.survivalcoding.ifkakao.MainViewModel
import com.survivalcoding.ifkakao.databinding.FragmentLoginDialogBinding

class LoginDialogFragment : DialogFragment() {

    private var _binding: FragmentLoginDialogBinding? = null
    private val binding get() = _binding!!
    private val viewModel by activityViewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isCancelable = true
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentLoginDialogBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.login.setOnClickListener {
            if (binding.username.text.isNullOrEmpty() || binding.password.text.isNullOrEmpty()) return@setOnClickListener
            viewModel.setLogin(binding.username.text.toString())
            dismiss()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}