package com.sopt.soptkathon.android.bckk.data.api.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PostArticleResponse(
    @SerialName("createdDateTime")
    val createdDateTime: String = "",
    @SerialName("postContent")
    val postContent: String = "",
    @SerialName("postDislikeCount")
    val postDislikeCount: Int = 0,
    @SerialName("postId")
    val postId: Int = 0,
    @SerialName("postImg")
    val postImg: String = ""
)