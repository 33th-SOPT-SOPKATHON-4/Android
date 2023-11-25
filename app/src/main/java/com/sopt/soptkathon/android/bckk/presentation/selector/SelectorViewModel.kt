package com.sopt.soptkathon.android.bckk.presentation.selector

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.soptkathon.android.bckk.data.api.ServicePool
import com.sopt.soptkathon.android.bckk.data.api.model.ArticlesResponse
import com.sopt.soptkathon.android.bckk.data.api.model.BaseResponse
import com.sopt.soptkathon.android.bckk.data.api.model.PostDisLikeRequest
import com.sopt.soptkathon.android.bckk.data.sharedpreference.SharedPreferenceContainer
import com.sopt.soptkathon.android.bckk.data.sharedpreference.sharedPreferences
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SelectorViewModel : ViewModel() {
    private val _currentPage = MutableStateFlow<Int>(0)
    val currentPage get() = _currentPage.asStateFlow()
    private val _firstSelect = MutableStateFlow<Long?>(null)
    val firstSelect get() = _firstSelect.asStateFlow()
    private val _secondSelect = MutableStateFlow<Long?>(null)
    val secondSelect = _secondSelect.asStateFlow()
    private val _thirdSelect = MutableStateFlow<Long?>(null)
    val thirdSelect = _thirdSelect.asStateFlow()
    private val _articleList = MutableStateFlow<List<ArticlesResponse.PostDto>>(listOf())
    val articleList = _articleList.asStateFlow()
    private val _postDislikeState = MutableStateFlow(false)
    val postDislikeState = _postDislikeState.asStateFlow()

    val selectedFlow: StateFlow<Long?> = combine(
        currentPage,
        firstSelect,
        secondSelect,
        thirdSelect
    ) { currentPage, firstSelect, secondSelect, thirdSelect ->
        when (currentPage) {
            0 -> firstSelect
            1 -> secondSelect
            2 -> thirdSelect
            else -> null
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), null)

    val isSelectorBtnEnabled: StateFlow<Boolean> = combine(
        currentPage,
        firstSelect,
        secondSelect,
        thirdSelect
    ) { currentPage, firstSelect, secondSelect, thirdSelect ->
        when (currentPage) {
            0 -> firstSelect != null
            1 -> secondSelect != null
            2 -> thirdSelect != null
            else -> false
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), false)

    init {
        getArticles()
    }

    fun setCurrentPage(position: Int) {
        _currentPage.value = position
    }

    fun setFirstSelect(position: Long) {
        _firstSelect.value = position
    }

    fun setSecondSelect(position: Long) {
        _secondSelect.value = position
    }

    fun setThirdSelect(position: Long) {
        _thirdSelect.value = position
    }

    fun modifySelectedValue(newValue: Long) {
        when (currentPage.value) {
            0 -> _firstSelect.value = newValue
            1 -> _secondSelect.value = newValue
            2 -> _thirdSelect.value = newValue
        }
    }

    fun getArticles() {
        viewModelScope.launch {
            runCatching {
                ServicePool.heungService.getArticles(SharedPreferenceContainer.getLocalUserId())
            }.onSuccess { value: BaseResponse<ArticlesResponse> ->
                _articleList.value = value.data.postListDto
            }.onFailure { exception: Throwable ->
                Log.e("exception", exception.message.toString())
            }
        }
    }

    fun postDisLike(requestBody: PostDisLikeRequest) {
        viewModelScope.launch {
            runCatching {
                ServicePool.heungService.postDislike(SharedPreferenceContainer.getLocalUserId(), requestBody)
            }.onSuccess {
                _postDislikeState.value = true
            }.onFailure {
                _postDislikeState.value = false
            }
        }
    }
}