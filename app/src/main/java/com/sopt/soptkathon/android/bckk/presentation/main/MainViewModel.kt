package com.sopt.soptkathon.android.bckk.presentation.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.soptkathon.android.bckk.data.api.ServicePool
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    fun login(androidId: String) {
        viewModelScope.launch {
            Log.d("MainViewModel", "login: $androidId")
            runCatching {
                ServicePool.heungService.login(androidId)
            }.onSuccess {
                Log.d("MainViewModel", "login: $it")
            }.onFailure {
                Log.e("MainViewModel", "login: $it")
            }
        }
    }
}
