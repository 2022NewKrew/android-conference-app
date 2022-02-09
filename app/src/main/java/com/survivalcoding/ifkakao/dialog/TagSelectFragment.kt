package com.survivalcoding.ifkakao.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import com.survivalcoding.ifkakao.MainViewModel
import com.survivalcoding.ifkakao.R
import com.survivalcoding.ifkakao.databinding.FragmentTagSelectDialogBinding
import kotlinx.coroutines.launch

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

        keywords = viewModel.keywords.value

        initText()
        binding.initBtn.setOnClickListener {
            keywords = listOf()
            resetText()
        }

        binding.cancelButton.setOnClickListener {
            dismiss()
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

    fun initText() {
        val tagContainer = binding.tagContainer as ViewGroup
        for (cnt in 0 until tagContainer.childCount) {
            val child: View = tagContainer.getChildAt(cnt)
            if (child is TextView && child.text.toString() in keywords) {
                child.backgroundTintList = ResourcesCompat.getColorStateList(
                    binding.root.resources,
                    R.color.teal_700,
                    null
                )
            }
        }
    }

    fun resetText() {
        val tagContainer = binding.tagContainer as ViewGroup
        for (cnt in 0 until tagContainer.childCount) {
            val child: View = tagContainer.getChildAt(cnt)
            if (child is TextView) {
                child.backgroundTintList = null
            }
        }
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
