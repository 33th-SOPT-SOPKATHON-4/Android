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
import retrofit2.http.Path

interface HeungService {
    @POST("/{ssaId}/login")
    suspend fun login(
        @Path("ssaId") ssaId: String,
        @Body request: LoginRequest,
    ): BaseResponse<LoginResponse>

    @GET("/{ssaId}/post")
    suspend fun getArticles(
        @Path("ssaId") ssaId: String,
    ): BaseResponse<ArticlesResponse>

    @POST("/{ssaId}/post")
    suspend fun postArticles(
        @Path("ssaId") ssaId: String,
        @Body request: ArticlesRequest,
    ): BaseResponse<Unit>

    @POST("/{ssaId}/dislike")
    suspend fun postDislike(
        @Path("ssaId") ssaId: String,
        @Body request: PostDisLikeRequest,
    ): BaseResponse<Unit>

    @GET("/{ssaId}/dislike")
    suspend fun getUserInfo(
        @Path("ssaId") ssaId: String,
    ): BaseResponse<UserResponse>
}
