package com.sopt.soptkathon.android.bckk.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import com.sopt.soptkathon.android.bckk.base.BindActivity
import com.sopt.soptkathon.android.bckk.databinding.ActivityHomeBinding

class HomeActivity : BindActivity<ActivityHomeBinding>() {
    private val homeViewModel by viewModels<HomeViewModel>()

    override fun setBinding(layoutInflater: LayoutInflater): ActivityHomeBinding {
        return ActivityHomeBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        homeClickListener()

        homeViewModel.getUserInfo("userId")
        homeViewModel.userInfoResult.observe(
            this,
            {
                binding.tvHomeTicketCount.text = it.toString()
            },
        )
    }

    private fun homeClickListener() {
        binding.run {
            ibHomeGoMyPage.setOnClickListener {
                // TODO 마이페이지 화면 전환
            }

            ibHomeGoAddArticle.setOnClickListener {
                // TODO 게시글 작성 화면 전환
            }

            ibHomeGoSelector.setOnClickListener {
                // TODO 질투 선택 화면 전환
            }
            tvHomeTicketCount.setOnClickListener {
                // 티켓 수
            }
        }
    }
}
