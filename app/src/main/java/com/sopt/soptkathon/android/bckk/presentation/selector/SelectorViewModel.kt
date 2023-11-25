package com.sopt.soptkathon.android.bckk.presentation.selector

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.soptkathon.android.bckk.data.api.ServicePool
import com.sopt.soptkathon.android.bckk.data.api.model.ArticlesResponse
import com.sopt.soptkathon.android.bckk.data.api.model.BaseResponse
import com.sopt.soptkathon.android.bckk.data.api.model.PostDisLikeRequest
import com.sopt.soptkathon.android.bckk.data.sharedpreference.SharedPreferenceContainer
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
    private val _articleList = MutableStateFlow<List<ArticlesResponse.PostDto>>(
        listOf(
            ArticlesResponse.PostDto(
                createdDateTime = "",
                postContent = "열심히 노력한 자 공모전을 수상하리,,,",
                postDislikeReactionCount = 0,
                postId = 1,
                postImg = "https://github.com/33th-SOPT-SOPKATHON-4/Android/assets/103172971/fdb204c2-7997-47e3-bb7c-851842554f18",
            ),
            ArticlesResponse.PostDto(
                createdDateTime = "",
                postContent = "직장병아리삐약삐약, 오늘 첫 칭찬 들은 썰 푼다.",
                postDislikeReactionCount = 0,
                postId = 2,
                postImg = "https://github.com/33th-SOPT-SOPKATHON-4/Android/assets/103172971/2895c8b9-8b66-4401-a907-c60c346fe551",
            ),
            ArticlesResponse.PostDto(
                createdDateTime = "",
                postContent = "300일은 껌이지 창민이랑 기념 여행 ><",
                postDislikeReactionCount = 0,
                postId = 3,
                postImg = "https://github.com/33th-SOPT-SOPKATHON-4/Android/assets/103172971/6fec2f81-ead5-4fb9-b27b-3840e3e9ca16",
            ),
            ArticlesResponse.PostDto(
                createdDateTime = "",
                postContent = "짜파게티에 계란후라이 넣으려고 했는데 쌍란 나옴!!!",
                postDislikeReactionCount = 0,
                postId = 4,
                postImg = "https://github.com/33th-SOPT-SOPKATHON-4/Android/assets/103172971/43ab9a13-a9ec-4f1e-8552-546dd67b8ab2",
            ),
            ArticlesResponse.PostDto(
                createdDateTime = "",
                postContent = "몽블랑 S/S 팝업스토어 초청 감사합니다",
                postDislikeReactionCount = 0,
                postId = 5,
                postImg = "https://github.com/33th-SOPT-SOPKATHON-4/Android/assets/103172971/3e9d1676-868d-4416-a5ec-3cd979737fed",
            ),
            ArticlesResponse.PostDto(
                createdDateTime = "",
                postContent = "평소에도 애용하던 이솝에서 협찬 제안이 ㅠㅠ",
                postDislikeReactionCount = 0,
                postId = 6,
                postImg = "https://github.com/33th-SOPT-SOPKATHON-4/Android/assets/103172971/6209e786-093c-437c-adf8-b630384ac774",
            ),
            ArticlesResponse.PostDto(
                createdDateTime = "",
                postContent = "5박 6일 나홀로 치앙마이 힐링 여행~",
                postDislikeReactionCount = 0,
                postId = 7,
                postImg = "https://github.com/33th-SOPT-SOPKATHON-4/Android/assets/103172971/8a729feb-b551-45c5-84cf-78bbb1aaa94d",
            ),
            ArticlesResponse.PostDto(
                createdDateTime = "",
                postContent = "생일 선물 언박싱하다가 하루 다 갈듯 ㅎㅎ",
                postDislikeReactionCount = 0,
                postId = 8,
                postImg = "https://github.com/33th-SOPT-SOPKATHON-4/Android/assets/103172971/0e3f6175-d216-4c83-b086-d4ca182d6bdd",
            ),
            ArticlesResponse.PostDto(
                createdDateTime = "",
                postContent = "중딩 때 친구들이랑 7주년 꺄악 앞으로도 잘 부탁해!",
                postDislikeReactionCount = 0,
                postId = 9,
                postImg = "https://github.com/33th-SOPT-SOPKATHON-4/Android/assets/103172971/0238392f-2e54-462a-8eb5-3f0cf29b88d3",
            ),
            ArticlesResponse.PostDto(
                createdDateTime = "",
                postContent = "열심히 돈을 모아모아 짱 맛있는 오마카세 가다!!!",
                postDislikeReactionCount = 0,
                postId = 10,
                postImg = "https://github.com/33th-SOPT-SOPKATHON-4/Android/assets/103172971/a6a5768d-84de-407e-9c51-9d36f661650c",
            ),
            ArticlesResponse.PostDto(
                createdDateTime = "",
                postContent = "소처럼 일해서 모은 월급, 호캉스에 플렉스 했다!",
                postDislikeReactionCount = 0,
                postId = 11,
                postImg = "https://github.com/33th-SOPT-SOPKATHON-4/Android/assets/103172971/1f9cc47a-3020-4267-af24-649fc7f97a79",
            ),
            ArticlesResponse.PostDto(
                createdDateTime = "",
                postContent = "이번에 취뽀 성공했다~!",
                postDislikeReactionCount = 0,
                postId = 12,
                postImg = "https://github.com/33th-SOPT-SOPKATHON-4/Android/assets/103172971/b78d587d-6c31-4404-9726-cd5510a73cf6",
            ),
        ),
    )

    val articleList = _articleList.asStateFlow()
    private val _postDislikeState = MutableStateFlow(false)
    val postDislikeState = _postDislikeState.asStateFlow()

    val selectedFlow: StateFlow<Long?> = combine(
        currentPage,
        firstSelect,
        secondSelect,
        thirdSelect,
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
        thirdSelect,
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
                ServicePool.heungService.getArticles(SharedPreferenceContainer.getLocalUserId()!!)
            }.onSuccess { value: BaseResponse<ArticlesResponse> ->
                viewModelScope.launch {
                    _articleList.value = value.data?.postListDto ?: listOf()
                }
            }.onFailure { exception: Throwable ->
                Log.e("exception", exception.message.toString())
            }
        }
    }

    fun postDisLike(requestBody: PostDisLikeRequest) {
        viewModelScope.launch {
            runCatching {
                ServicePool.heungService.postDislike(SharedPreferenceContainer.getLocalUserId()!!, requestBody)
            }.onSuccess {
                _postDislikeState.value = true
            }.onFailure {
                Log.e("exception", it.message.toString())
                _postDislikeState.value = false
            }
        }
    }
}
