package com.sopt.soptkathon.android.bckk.presentation.mypage

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.soptkathon.android.bckk.data.api.ServicePool
import com.sopt.soptkathon.android.bckk.data.api.model.UserResponse
import kotlinx.coroutines.launch

class MyPageViewModel : ViewModel() {

    private val _userDto = MutableLiveData<UserResponse.UserDto>()
    val userDto: LiveData<UserResponse.UserDto> get() = _userDto

    private val _postDtoList = MutableLiveData<List<UserResponse.UserDto.PostDto>>()
    val postDtoList: LiveData<List<UserResponse.UserDto.PostDto>> get() = _postDtoList

    fun getUserInfo(ssaId: String) {
        viewModelScope.launch {
            runCatching {
                ServicePool.heungService.getUserInfo(ssaId)
            }.onSuccess { response ->
                _userDto.value = response.data.userDto
                _postDtoList.value = response.data.userDto.postListDto
            }.onFailure {
                Log.e("NetworkTest", "error:$it")
            }
        }
    }
}
