package com.sopt.soptkathon.android.bckk.presentation.article

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
        setContentView(binding.root)
        initView()
        initObserver()
    }

    private fun initObserver() {
        viewModel.mutableList.observe(this) {
            if (it) {
                TwoButtonDialogFragment().apply {
                    setTitleText("자랑하기 작성 완료!")
                    setMessageText("자랑하기가 작성되었습니다.")
                    setCancelListener("내 자랑 보러가기") {
                        // TODO 내 자랑 보러가기
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
            viewModel.submitArticle(ContentUriRequestBody(this, imageUri).toFormData())
        }
    }

    override fun setBinding(layoutInflater: LayoutInflater): ActivityAddArticleBinding {
        return ActivityAddArticleBinding.inflate(layoutInflater)
    }
}
