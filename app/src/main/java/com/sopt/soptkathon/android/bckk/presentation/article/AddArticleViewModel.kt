package com.sopt.soptkathon.android.bckk.presentation.article

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.soptkathon.android.bckk.data.api.ServicePool
import com.sopt.soptkathon.android.bckk.data.sharedpreference.SharedPreferenceContainer
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody

class AddArticleViewModel : ViewModel() {
    val mutableList = MutableLiveData<Boolean>()
    fun submitArticle(imageRequestBody: MultipartBody.Part, content: String) {
        viewModelScope.launch {
            runCatching {
                ServicePool.heungService.postArticles(
                    SharedPreferenceContainer.getLocalUserId() ?: let {
                        mutableList.postValue(false)
                        return@launch
                    },
                    postImg = imageRequestBody,
                    postContent = content.toRequestBody("text/plain".toMediaType()),
                )
            }.onSuccess {
                mutableList.postValue(true)
                Log.d("AddArticle", it.toString())
            }.onFailure {
                Log.e("AddArticle", it.toString())
                mutableList.postValue(false)
            }
        }
    }
}
