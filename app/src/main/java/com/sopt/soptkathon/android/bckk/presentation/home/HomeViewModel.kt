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

    private val _ticketCount = MutableLiveData<Int>()
    val ticketCount: LiveData<Int> get() = _ticketCount

    private val _isGoArticleButtonClicked = MutableLiveData<Boolean>()
    val isGoArticleButtonClicked: LiveData<Boolean> get() = _isGoArticleButtonClicked

    // api로 뽑아야 하는 정보 : 티켓수만! 티켓수가 1개 이상이면 자랑하기 이동 가능, 버튼 활성화
    fun getUserInfo(userId: String) {
        viewModelScope.launch {
            runCatching {
                ServicePool.heungService.getUserInfo(userId)
            }.onSuccess { userResponse ->
                Log.d("NetworkTest", userResponse.toString())
                val ticketCount: Int = UserResponse().userDto.ticketCount
                _ticketCount.value = ticketCount

                _isGoArticleButtonClicked.value = ticketCount > 0
            }.onFailure {
                Log.e("NetworkTest", "error:$it")
            }
        }
    }
}
