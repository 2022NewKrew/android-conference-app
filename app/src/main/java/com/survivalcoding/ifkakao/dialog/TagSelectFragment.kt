package com.survivalcoding.ifkakao.dialog

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.widget.TextViewCompat
import androidx.databinding.BindingAdapter
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.survivalcoding.ifkakao.MainViewModel
import com.survivalcoding.ifkakao.R
import com.survivalcoding.ifkakao.databinding.FragmentTagSelectDialogBinding

class TagSelectFragment : DialogFragment() {


    private var _binding: FragmentTagSelectDialogBinding? = null
    private val binding get() = _binding!!
    private val viewModel by activityViewModels<MainViewModel>()
    private var keywords = listOf<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.dialog_fullscreen)
        isCancelable = true
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTagSelectDialogBinding.inflate(inflater, container, false)
        binding.tagFragment = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.AI.setOnClickListener {
            keywords = listOf("머신러닝/AI")
            it.setBackgroundColor(0x00ff00)
        }

        binding.cancelButton.setOnClickListener {
            dismiss()
        }
        binding.initBtn.setOnClickListener {
            initView()
        }
        binding.confirmBtn.setOnClickListener {
            viewModel.updateKeyWords(keywords)
            dismiss()
        }


    }

    private fun initView() {
        viewModel.keywords.value = listOf<String>()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun toggleText(textView: View) {
        if (textView.backgroundTintList == null) {
            keywords = keywords.plus((textView as TextView).text.toString())
            textView.backgroundTintList = ResourcesCompat.getColorStateList(
                binding.root.resources,
                R.color.teal_700,
                null
            )
        } else {
            keywords = keywords.filter { keyword ->
                keyword != (textView as TextView).text.toString()
            }
            textView.backgroundTintList = null
        }


    }


}
