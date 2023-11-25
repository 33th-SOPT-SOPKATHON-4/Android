package com.sopt.soptkathon.android.bckk.data.api

import com.sopt.soptkathon.android.bckk.data.api.model.ArticlesResponse
import com.sopt.soptkathon.android.bckk.data.api.model.BaseResponse
import com.sopt.soptkathon.android.bckk.data.api.model.LoginResponse
import com.sopt.soptkathon.android.bckk.data.api.model.PostDisLikeRequest
import com.sopt.soptkathon.android.bckk.data.api.model.UserResponse
import okhttp3.MultipartBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface HeungService {
    @POST("/user/login/{ssaId}")
    suspend fun login(
        @Path("ssaId") ssaId: String,
    ): BaseResponse<LoginResponse>

    @GET("/{ssaId}/post")
    suspend fun getArticles(
        @Path("ssaId") ssaId: String,
    ): BaseResponse<ArticlesResponse>

    @POST("/{ssaId}/post")
    suspend fun postArticles(
        @Path("ssaId") ssaId: String,
        @Part image: MultipartBody.Part,
        @Part postContent: String = "",
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
