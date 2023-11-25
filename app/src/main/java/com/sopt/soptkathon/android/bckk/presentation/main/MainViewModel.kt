package com.sopt.soptkathon.android.bckk.presentation.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.soptkathon.android.bckk.data.sharedpreference.SharedPreferenceContainer
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    fun login(androidId: String) {
        viewModelScope.launch {
            runCatching {
            }.onSuccess {
                SharedPreferenceContainer.setLocalUserId(androidId)
                Log.d("MainViewModel", "login: $androidId")
            }.onFailure {
                Log.e("MainViewModel", "login: $it")
            }
        }
    }
}
