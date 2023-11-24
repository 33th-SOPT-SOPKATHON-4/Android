package com.sopt.soptkathon.android.bckk.presentation

import android.os.Bundle
import android.view.LayoutInflater
import com.sopt.soptkathon.android.bckk.base.BindActivity
import com.sopt.soptkathon.android.bckk.databinding.ActivityMainBinding

class MainActivity : BindActivity<ActivityMainBinding>() {

    override fun setBinding(layoutInflater: LayoutInflater): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}
