package com.survivalcoding.ifkakao.presentation.dialog

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Point
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.survivalcoding.ifkakao.R
import com.survivalcoding.ifkakao.databinding.FragmentLoginInfoDialogBinding
import com.survivalcoding.ifkakao.presentation.ActivityViewModel
import com.survivalcoding.ifkakao.presentation.LoginEvent
import com.survivalcoding.ifkakao.presentation.LoginState.*

class LoginInfoDialogFragment : DialogFragment() {

    private var _binding: FragmentLoginInfoDialogBinding? = null
    private val binding get() = _binding!!

    private val viewModel by activityViewModels<ActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isCancelable = true
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginInfoDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.closeButton.setOnClickListener { dismiss() }
        binding.maintainCheckButton.setOnClickListener { viewModel.onEvent(LoginEvent.MaintainLogin) }
        viewModel.isMaintainLogin.observe(viewLifecycleOwner) {
            if (it) {
                binding.maintainCheckButton.setImageResource(R.drawable.ic_checked_circle)
                binding.maintainCheckButton.imageTintList =
                    ColorStateList.valueOf(Color.parseColor("#ffe812"))
            } else {
                binding.maintainCheckButton.setImageResource(R.drawable.ic_check_circle)
                binding.maintainCheckButton.imageTintList =
                    ColorStateList.valueOf(Color.parseColor("#a0a0a0"))
            }
        }

        binding.loginButton.setOnClickListener {
            val id = binding.loginId.text.toString()
            val password = binding.loginPassword.text.toString()
            viewModel.onEvent(LoginEvent.Login(id, password))
        }
        viewModel.isLogin.observe(viewLifecycleOwner) {
            when (it) {
                null -> {}
                START -> {}
                FAIL -> {
                    binding.loginPassword.setText("")
                    Toast.makeText(
                        requireContext(),
                        "아이디 혹은 비밀번호가 틀렸습니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                SUCCESS -> dismiss()
                EMPTY_ID -> Toast.makeText(
                    requireContext(),
                    "아이디를 입력해주세요.",
                    Toast.LENGTH_SHORT
                ).show()
                EMPTY_PASSWORD -> Toast.makeText(
                    requireContext(),
                    "비밀번호를 입력해주세요.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        context?.dialogFragmentResize(this@LoginInfoDialogFragment, 0.75f)
    }

    private fun Context.dialogFragmentResize(
        dialogFragment: DialogFragment,
        width: Float,
    ) {
        val windowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager

        if (Build.VERSION.SDK_INT < 30) {

            val display = windowManager.defaultDisplay
            val size = Point()

            display.getSize(size)

            val window = dialogFragment.dialog?.window

            val x = (size.x * width).toInt()
            val y = ViewGroup.LayoutParams.WRAP_CONTENT
            window?.setLayout(x, y)

        } else {

            val rect = windowManager.currentWindowMetrics.bounds

            val window = dialogFragment.dialog?.window

            val x = (rect.width() * width).toInt()
            val y = ViewGroup.LayoutParams.WRAP_CONTENT

            window?.setLayout(x, y)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}