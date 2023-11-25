package com.sopt.soptkathon.android.bckk.presentation.login

import android.os.Bundle
import android.view.LayoutInflater
import com.sopt.soptkathon.android.bckk.base.BindActivity
import com.sopt.soptkathon.android.bckk.databinding.ActivityLoginBinding

class LoginActivity : BindActivity<ActivityLoginBinding>() {

    override fun setBinding(layoutInflater: LayoutInflater): ActivityLoginBinding {
        return ActivityLoginBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}
