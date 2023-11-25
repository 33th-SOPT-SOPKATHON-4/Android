package com.sopt.soptkathon.android.bckk.presentation.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import com.sopt.soptkathon.android.bckk.R
import com.sopt.soptkathon.android.bckk.base.BindActivity
import com.sopt.soptkathon.android.bckk.databinding.ActivityHomeBinding
import com.sopt.soptkathon.android.bckk.presentation.article.AddArticleActivity
import com.sopt.soptkathon.android.bckk.presentation.mypage.MyPageActivity

class HomeActivity : BindActivity<ActivityHomeBinding>() {
    private val homeViewModel by viewModels<HomeViewModel>()

    override fun setBinding(layoutInflater: LayoutInflater): ActivityHomeBinding {
        return ActivityHomeBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.run {
            ibHomeGoMyPage.setOnClickListener {
                // TODO 마이페이지 화면 전환
                intent = Intent(applicationContext, MyPageActivity::class.java)
                startActivity(intent)
            }

            ibHomeGoAddArticle.setOnClickListener {
                // TODO 게시글 작성 화면 전환
                intent = Intent(applicationContext, AddArticleActivity::class.java)
                startActivity(intent)
            }

            ibHomeGoSelector.setOnClickListener {
                // TODO 질투 선택 화면 전환
            }
            tvHomeTicketCount.setOnClickListener {
                // 티켓 수
            }
        }

        homeViewModel.getUserInfo("userId") // TODO 유저 아이디 추가 String
        homeViewModel.ticketCount.observe(
            this,
        ) { ticketCount ->
            binding.tvHomeTicketCount.text = ticketCount.toString()
        }
        homeViewModel.isGoArticleButtonClicked.observe(
            this,
        ) { isButtonClicked ->
            binding.ibHomeGoAddArticle.isEnabled = isButtonClicked
            if (isButtonClicked == false) {
                binding.ibHomeGoAddArticle.setImageResource(R.drawable.dim_pink)
            }
        }
    }
}
