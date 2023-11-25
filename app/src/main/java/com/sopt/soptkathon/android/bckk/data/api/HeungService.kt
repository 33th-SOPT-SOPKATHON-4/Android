package com.sopt.soptkathon.android.bckk.data.api

import com.sopt.soptkathon.android.bckk.data.api.model.ArticlesRequest
import com.sopt.soptkathon.android.bckk.data.api.model.ArticlesResponse
import com.sopt.soptkathon.android.bckk.data.api.model.BaseResponse
import com.sopt.soptkathon.android.bckk.data.api.model.LoginRequest
import com.sopt.soptkathon.android.bckk.data.api.model.LoginResponse
import com.sopt.soptkathon.android.bckk.data.api.model.PostDisLikeRequest
import com.sopt.soptkathon.android.bckk.data.api.model.UserResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface HeungService {
    @POST("/{ssaId}/login")
    suspend fun login(
        @Body request: LoginRequest,
    ): BaseResponse<LoginResponse>

    @GET("/{ssaId}/post")
    suspend fun getArticles(): BaseResponse<ArticlesResponse>

    @POST("/{ssaId}/post")
    suspend fun postArticles(
        @Body request: ArticlesRequest,
    ): BaseResponse<Unit>

    @POST("/{ssaId}/dislike")
    suspend fun postDislike(
        @Body request: PostDisLikeRequest,
    ): BaseResponse<Unit>

    @GET("/{ssaId}/dislike")
    suspend fun getUserInfo(): BaseResponse<UserResponse>
}
