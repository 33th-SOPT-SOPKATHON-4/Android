package com.sopt.soptkathon.android.bckk.presentation.mypage

import android.os.Bundle
import android.view.LayoutInflater
import com.sopt.soptkathon.android.bckk.base.BindActivity
import com.sopt.soptkathon.android.bckk.databinding.ActivityMyPageBinding

class MyPageActivity: BindActivity<ActivityMyPageBinding>() {

    override fun setBinding(layoutInflater: LayoutInflater): ActivityMyPageBinding {
        return ActivityMyPageBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

}