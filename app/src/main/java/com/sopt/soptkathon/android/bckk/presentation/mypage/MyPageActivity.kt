package com.sopt.soptkathon.android.bckk.presentation.mypage

import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.sopt.soptkathon.android.bckk.R
import com.sopt.soptkathon.android.bckk.base.BindActivity
import com.sopt.soptkathon.android.bckk.data.sharedpreference.SharedPreferenceContainer
import com.sopt.soptkathon.android.bckk.databinding.ActivityMyPageBinding

class MyPageActivity : BindActivity<ActivityMyPageBinding>() {

    private val viewModel by viewModels<MyPageViewModel>()
    private lateinit var myPageAdapter: MyPageAdapter

    override fun setBinding(layoutInflater: LayoutInflater): ActivityMyPageBinding {
        return ActivityMyPageBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMyPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        myPageAdapter = MyPageAdapter()

        viewModel.userDto.observe(this) { userDto ->
            val numberOfUploadedArticle = userDto.postList.size
            setLevelImage(numberOfUploadedArticle)
            binding.tvMyPageIntro.text = "${userDto.nickname}님은 ${numberOfUploadedArticle}번 자랑했어요!"
        }

        viewModel.postDtoList.observe(this) { postList ->
            myPageAdapter.submitList(postList)
            binding.rvMyPageUploaded.apply {
                layoutManager = LinearLayoutManager(this@MyPageActivity)
                adapter = myPageAdapter
            }
        }

        viewModel.getUserInfo(SharedPreferenceContainer.getLocalUserId() ?: "")
    }

    private fun setLevelImage(numberOfUploadedArticle: Int) {
        when {
            numberOfUploadedArticle in 0..4 -> binding.ivMyPageProfile.setImageResource(R.drawable.img_level_1)
            numberOfUploadedArticle in 5..9 -> binding.ivMyPageProfile.setImageResource(R.drawable.img_level_2)
            numberOfUploadedArticle >= 10 -> binding.ivMyPageProfile.setImageResource(R.drawable.img_level_3)
            else -> {
                binding.ivMyPageProfile.setImageResource(R.drawable.img_level_1)
            }
        }
    }
}
