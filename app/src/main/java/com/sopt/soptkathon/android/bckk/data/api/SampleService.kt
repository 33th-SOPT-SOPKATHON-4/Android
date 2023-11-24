package com.sopt.soptkathon.android.bckk.data.api

import retrofit2.Call
import retrofit2.http.GET

interface SampleService {
    @GET("/sample")
    fun getSample(): Call<String>

    @GET("/sample")
    suspend fun getSample2(): String
}
