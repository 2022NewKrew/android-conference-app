package com.survivalcoding.ifkakao.presentation.dialog

import android.content.Context
import android.graphics.Point
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import com.survivalcoding.ifkakao.databinding.FragmentLoginDialogBinding

class LoginDialogFragment : DialogFragment() {

    private var _binding: FragmentLoginDialogBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isCancelable = true
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        context?.dialogFragmentResize(this@LoginDialogFragment, 0.75f, 0.3f)
    }

    private fun Context.dialogFragmentResize(
        dialogFragment: DialogFragment,
        width: Float,
        height: Float
    ) {
        val windowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager

        if (Build.VERSION.SDK_INT < 30) {

            val display = windowManager.defaultDisplay
            val size = Point()

            display.getSize(size)

            val window = dialogFragment.dialog?.window

            val x = (size.x * width).toInt()
//            val y = (size.y * height).toInt()
            val y = ViewGroup.LayoutParams.WRAP_CONTENT
            window?.setLayout(x, y)

        } else {

            val rect = windowManager.currentWindowMetrics.bounds

            val window = dialogFragment.dialog?.window

            val x = (rect.width() * width).toInt()
//            val y = (rect.height() * height).toInt()
            val y = ViewGroup.LayoutParams.WRAP_CONTENT

            window?.setLayout(x, y)
        }
    }
}