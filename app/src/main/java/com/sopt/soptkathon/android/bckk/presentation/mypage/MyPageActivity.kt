package com.sopt.soptkathon.android.bckk.presentation.mypage

import android.os.Bundle
import android.view.LayoutInflater
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sopt.soptkathon.android.bckk.base.BindActivity
import com.sopt.soptkathon.android.bckk.databinding.ActivityMyPageBinding

class MyPageActivity : BindActivity<ActivityMyPageBinding>() {

    private lateinit var viewModel: MyPageViewModel
    private lateinit var myPageAdapter: MyPageAdapter

    override fun setBinding(layoutInflater: LayoutInflater): ActivityMyPageBinding {
        return ActivityMyPageBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMyPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MyPageViewModel::class.java]

        viewModel.userDto.observe(this) { userDto ->
            binding.tvMyPageIntro.text = "${userDto.nickName}님의 자랑들이에요!"
        }

        viewModel.postDtoList.observe(this) { postList ->
            myPageAdapter = MyPageAdapter(postList)
            binding.rvMyPageUploaded.apply {
                layoutManager = LinearLayoutManager(this@MyPageActivity)
                adapter = myPageAdapter
            }
        }

        val ssaId = "your_ssa_id"
        viewModel.fetchData(ssaId)
    }

}