package com.sopt.soptkathon.android.bckk.presentation.article

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.soptkathon.android.bckk.data.api.ServicePool
import kotlinx.coroutines.launch
import okhttp3.MultipartBody

class AddArticleViewModel : ViewModel() {
    fun submitArticle(imageRequestBody: MultipartBody.Part) {
        viewModelScope.launch {
            ServicePool.heungService.postArticles("", imageRequestBody)
        }
    }
}
