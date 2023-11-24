package com.sopt.soptkathon.android.bckk.presentation.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sopt.soptkathon.android.bckk.base.BindFragment
import com.sopt.soptkathon.android.bckk.databinding.ActivityLoginBinding

class LoginFragment : BindFragment<ActivityLoginBinding>() {

    override fun setBinding(layoutInflater: LayoutInflater): ActivityLoginBinding {
        return ActivityLoginBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {

    }
}
