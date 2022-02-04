package com.survivalcoding.ifkakao.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.survivalcoding.ifkakao.MainViewModel
import com.survivalcoding.ifkakao.R
import com.survivalcoding.ifkakao.databinding.FragmentLoginDialogBinding
import com.survivalcoding.ifkakao.databinding.FragmentTagSelectDialogBinding

class TagSelectFragment : DialogFragment() {


    private var _binding: FragmentTagSelectDialogBinding? = null
    private val binding get() = _binding!!
    private val viewModel by activityViewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.dialog_fullscreen)
        isCancelable = true
    }

    //field
    //contentspeaker company
    //relationlist tech classification (기술)
    //relationlist classification (서비스.비즈니스)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTagSelectDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}