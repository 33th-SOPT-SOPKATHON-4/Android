package com.sopt.soptkathon.android.bckk.presentation.article

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import coil.load
import com.sopt.soptkathon.android.bckk.base.BindActivity
import com.sopt.soptkathon.android.bckk.data.ContentUriRequestBody
import com.sopt.soptkathon.android.bckk.databinding.ActivityAddArticleBinding
import com.sopt.soptkathon.android.bckk.presentation.TwoButtonDialogFragment
import com.sopt.soptkathon.android.bckk.presentation.home.HomeActivity
import com.sopt.soptkathon.android.bckk.presentation.mypage.MyPageActivity
import com.sopt.soptkathon.android.bckk.util.showToast

class AddArticleActivity : BindActivity<ActivityAddArticleBinding>() {

    private val viewModel by viewModels<AddArticleViewModel>()

    private var imageUri = Uri.EMPTY

    private val launcher = registerForActivityResult(ActivityResultContracts.GetContent()) { imageUri: Uri? ->
        this.imageUri = imageUri ?: Uri.EMPTY
        binding.ivImage.load(imageUri)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        initObserver()
    }

    private fun initObserver() {
        viewModel.mutableList.observe(this) {
            if (it) {
                TwoButtonDialogFragment().apply {
                    setTitleText("자랑하기 작성 완료!")
                    setMessageText("자랑하기가 작성되었습니다.")
                    setOkListener("질투나러 가기") {
                        val intent = Intent(this@AddArticleActivity, HomeActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                        startActivity(intent)
                    }
                    setCancelListener("내 자랑 보러가기") {
                        val intent = Intent(this@AddArticleActivity, MyPageActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                        startActivity(intent)
                    }
                }.showAllowingStateLoss(supportFragmentManager, "")
            } else {
                showToast("게시글 등록에 실패하였습니다.")
            }
        }
    }

    private fun initView() {
        binding.ivImage.setOnClickListener {
            launcher.launch("image/*")
        }
        binding.btSubmit.setOnClickListener {
            if (imageUri == Uri.EMPTY) {
                showToast("이미지를 선택해주세요.")
                return@setOnClickListener
            }
            viewModel.submitArticle(
                ContentUriRequestBody(
                    context = this,
                    uri = imageUri,
                ).toFormData(),
                content = binding.etContent.text.toString(),
            )
        }
    }

    override fun setBinding(layoutInflater: LayoutInflater): ActivityAddArticleBinding {
        return ActivityAddArticleBinding.inflate(layoutInflater)
    }
}
