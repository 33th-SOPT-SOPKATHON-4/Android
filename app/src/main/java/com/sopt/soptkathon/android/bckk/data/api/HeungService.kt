package com.sopt.soptkathon.android.bckk.data.api

import com.sopt.soptkathon.android.bckk.data.api.model.ArticlesResponse
import com.sopt.soptkathon.android.bckk.data.api.model.BaseResponse
import com.sopt.soptkathon.android.bckk.data.api.model.LoginResponse
import com.sopt.soptkathon.android.bckk.data.api.model.PostArticleResponse
import com.sopt.soptkathon.android.bckk.data.api.model.PostDisLikeRequest
import com.sopt.soptkathon.android.bckk.data.api.model.UserResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
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

    @Multipart
    @POST("/{ssaId}/post")
    suspend fun postArticles(
        @Path("ssaId") ssaId: String = "123126",
        @Part postImg: MultipartBody.Part,
        @Part("postContent") postContent: RequestBody,
    ): BaseResponse<PostArticleResponse>

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
