package com.sopt.soptkathon.android.bckk.presentation.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.soptkathon.android.bckk.data.api.ServicePool
import com.sopt.soptkathon.android.bckk.data.api.model.UserResponse
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _userInfoResult = MutableLiveData<Boolean>()
    val userInfoResult: LiveData<Boolean> get() = _userInfoResult
    val isGoArticleButtonClicked = MutableLiveData<Boolean>()

    // api로 뽑아야 하는 정보 : 티켓수만! 티켓수가 1개 이상이면 자랑하기 이동 가능, 버튼 활성화
    fun getUserInfo(userId: String) {
        viewModelScope.launch {
            runCatching {
                ServicePool.heungService.getUserInfo(userId) // TODO 유저 아이디 추가 String
            }.onSuccess { userResponse ->
                Log.d("NetworkTest", userResponse.toString())
                _userInfoResult.value = true
                val ticketCount = UserResponse().userDto.ticketCount
                if (ticketCount > 0) {
                    isGoArticleButtonClicked.value = true
                }
            }.onFailure {
                Log.e("NetworkTest", "error:$it")
            }
        }
    }

    fun onClickGoArticleBtn() {
        isGoArticleButtonClicked.value = true
    }
}
