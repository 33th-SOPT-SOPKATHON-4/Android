package com.sopt.soptkathon.android.bckk.presentation.selector

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.soptkathon.android.bckk.data.api.model.ArticlesResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

class SelectorViewModel : ViewModel() {
    private val _currentPage = MutableStateFlow<Int?>(0)
    val currentPage get() = _currentPage.asStateFlow()
    private val _firstSelect = MutableStateFlow<Long?>(null)
    val firstSelect get() = _firstSelect.asStateFlow()
    private val _secondSelect = MutableStateFlow<Long?>(null)
    val secondSelect = _secondSelect.asStateFlow()
    private val _thirdSelect = MutableStateFlow<Long?>(null)
    val thirdSelect = _thirdSelect.asStateFlow()
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

    val dummyPost = listOf(
        ArticlesResponse.PostDto(
            createdDateTime = "",
            postContent = "11111",
            postDislikeReactionCount = 0,
            postId = 1,
            postImg = ""
        ),
        ArticlesResponse.PostDto(
            createdDateTime = "",
            postContent = "22222",
            postDislikeReactionCount = 0,
            postId = 2,
            postImg = ""
        ),
        ArticlesResponse.PostDto(
            createdDateTime = "",
            postContent = "33333",
            postDislikeReactionCount = 0,
            postId = 3,
            postImg = ""
        ),
        ArticlesResponse.PostDto(
            createdDateTime = "",
            postContent = "44444",
            postDislikeReactionCount = 0,
            postId = 4,
            postImg = ""
        ),
        ArticlesResponse.PostDto(
            createdDateTime = "",
            postContent = "55555",
            postDislikeReactionCount = 0,
            postId = 5,
            postImg = ""
        ),
        ArticlesResponse.PostDto(
            createdDateTime = "",
            postContent = "66666",
            postDislikeReactionCount = 0,
            postId = 6,
            postImg = ""
        ),
        ArticlesResponse.PostDto(
            createdDateTime = "",
            postContent = "77777",
            postDislikeReactionCount = 0,
            postId = 7,
            postImg = ""
        ),
        ArticlesResponse.PostDto(
            createdDateTime = "",
            postContent = "88888",
            postDislikeReactionCount = 0,
            postId = 8,
            postImg = ""
        ),
        ArticlesResponse.PostDto(
            createdDateTime = "",
            postContent = "99999",
            postDislikeReactionCount = 0,
            postId = 9,
            postImg = ""
        ),
        ArticlesResponse.PostDto(
            createdDateTime = "",
            postContent = "10101010",
            postDislikeReactionCount = 0,
            postId = 10,
            postImg = ""
        ),
        ArticlesResponse.PostDto(
            createdDateTime = "",
            postContent = "111111111",
            postDislikeReactionCount = 0,
            postId = 11,
            postImg = ""
        ),
        ArticlesResponse.PostDto(
            createdDateTime = "",
            postContent = "12",
            postDislikeReactionCount = 0,
            postId = 12,
            postImg = ""
        )
    )

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
}