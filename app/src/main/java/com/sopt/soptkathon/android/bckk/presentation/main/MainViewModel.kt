package com.sopt.soptkathon.android.bckk.presentation.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.soptkathon.android.bckk.data.api.ServicePool
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    fun getSample() {
        ServicePool.sampleService.getSample().enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        Log.d("NetworkTest", it)
                    }
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.d("NetworkTest", "error:$t")
            }
        })
    }

    fun getSample2() {
        viewModelScope.launch {
            runCatching {
                ServicePool.sampleService.getSample2()
            }.onSuccess {
                Log.d("NetworkTest", it)
            }.onFailure {
                Log.e("NetworkTest", "error:$it")
            }
        }
    }
}
