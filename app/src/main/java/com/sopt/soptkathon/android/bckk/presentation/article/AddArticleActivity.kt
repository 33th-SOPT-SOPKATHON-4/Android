package com.sopt.soptkathon.android.bckk.presentation.article

import android.os.Bundle
import android.view.LayoutInflater
import com.sopt.soptkathon.android.bckk.base.BindActivity
import com.sopt.soptkathon.android.bckk.databinding.ActivityAddArticleBinding

class AddArticleActivity : BindActivity<ActivityAddArticleBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initView()
    }

    private fun initView() {

    }

    override fun setBinding(layoutInflater: LayoutInflater): ActivityAddArticleBinding {
        return ActivityAddArticleBinding.inflate(layoutInflater)
    }
}
