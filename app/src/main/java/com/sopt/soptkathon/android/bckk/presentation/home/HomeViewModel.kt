package com.sopt.soptkathon.android.bckk.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val _userInfoResult = MutableLiveData<Boolean>()
    val userInfoResult: LiveData<Boolean> get() = _userInfoResult
}
