package com.survivalcoding.ifkakao.presentation.common

import android.app.Activity
import androidx.appcompat.app.AlertDialog
import com.survivalcoding.ifkakao.R
import com.survivalcoding.ifkakao.databinding.CustomDialogWithOneButtonBinding

fun customDialogWithOneButton(
    activity: Activity,
    message: String,
    buttonText: String,
    onButtonClick: () -> Unit
): AlertDialog {
    val binding = CustomDialogWithOneButtonBinding.inflate(activity.layoutInflater).apply {
        textView.text = message
        buttonView.text = buttonText
    }

    return AlertDialog.Builder(activity, R.style.CustomAlertDialog)
        .setView(binding.root)
        .setCancelable(false)
        .create()
        .apply {
            window?.setBackgroundDrawableResource(R.color.transparent)
            binding.buttonView.setOnClickListener {
                onButtonClick()
                dismiss()
            }
        }
}