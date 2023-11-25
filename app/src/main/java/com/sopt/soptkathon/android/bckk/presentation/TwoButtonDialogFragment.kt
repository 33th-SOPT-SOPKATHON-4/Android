package com.sopt.soptkathon.android.bckk.presentation

import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.LayoutInflater
import android.view.View
import androidx.core.view.isGone
import com.sopt.soptkathon.android.bckk.base.BaseViewBindingDialogFragment
import com.sopt.soptkathon.android.bckk.databinding.FragmentTwobuttonDialogBinding

class TwoButtonDialogFragment : BaseViewBindingDialogFragment<FragmentTwobuttonDialogBinding>() {
    private var titleId: Int? = null
    private var titleText = ""
    private var messageId: Int? = null
    private var messageText = ""
    private var spannableString: SpannableStringBuilder? = null

    private var okButtonText: String? = null
    private var okListener: (() -> Unit)? = null

    private var cancelButtonText: String? = null
    private var cancelListener: (() -> Unit)? = null

    override fun setBinding(layoutInflater: LayoutInflater): FragmentTwobuttonDialogBinding {
        return FragmentTwobuttonDialogBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isCancelable = false
        initViews()
    }

    override fun onResume() {
        super.onResume()
        context?.let {
            dialogWidthPercent(it, dialog)
        }
    }

    private fun initViews() = with(binding) {
        initTitle()
        initMessage()
        initOkButton()
        initCancelButton()
    }

    private fun FragmentTwobuttonDialogBinding.initTitle() {
        titleId?.let {
            tvTitle.text = getString(it)
        } ?: run {
            tvTitle.text = titleText.ifEmpty { null }
            tvTitle.isGone = titleText.isEmpty()
        }
    }

    private fun FragmentTwobuttonDialogBinding.initMessage() {
        messageId?.let {
            tvMessage.text = getString(it)
        } ?: run {
            tvMessage.text = messageText.ifEmpty { spannableString }
            tvMessage.isGone = messageText.isEmpty() && spannableString == null
        }
    }

    private fun FragmentTwobuttonDialogBinding.initOkButton() {
        okButtonText?.let { btOk.text = it } ?: run { btOk.isGone = true }
        btOk.setOnClickListener {
            okListener?.invoke()
            dismissAllowingStateLoss()
        }
    }

    private fun FragmentTwobuttonDialogBinding.initCancelButton() {
        okButtonText?.let { btOk.text = it } ?: run { btOk.isGone = true }
        btOk.setOnClickListener {
            okListener?.invoke()
            dismissAllowingStateLoss()
        }
    }

    fun setTitleId(_titleId: Int) {
        titleId = _titleId
    }

    fun setTitleText(_titleText: String) {
        titleText = _titleText
    }

    fun setMessageId(_messageId: Int) {
        messageId = _messageId
    }

    fun setMessageText(_messageText: String) {
        messageText = _messageText
    }

    fun setMessageText(_spannableString: SpannableStringBuilder) {
        spannableString = _spannableString
    }

    fun setOkListener(text: String, listener: (() -> Unit)? = null) {
        okButtonText = text
        okListener = listener
    }

    fun setCancelListener(text: String, listener: (() -> Unit)? = null) {
        cancelButtonText = text
        cancelListener = listener
    }
}
