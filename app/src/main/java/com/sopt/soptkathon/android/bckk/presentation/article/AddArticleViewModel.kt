package com.sopt.soptkathon.android.bckk.presentation.article

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.soptkathon.android.bckk.data.api.ServicePool
import kotlinx.coroutines.launch
import okhttp3.MultipartBody

class AddArticleViewModel : ViewModel() {
    val mutableList = MutableLiveData<Boolean>()
    fun submitArticle(imageRequestBody: MultipartBody.Part) {
        viewModelScope.launch {
            runCatching {
                ServicePool.heungService.postArticles("", imageRequestBody)
            }.onSuccess {
                mutableList.postValue(true)
            }.onFailure {
                mutableList.postValue(false)
            }
        }
    }
}
