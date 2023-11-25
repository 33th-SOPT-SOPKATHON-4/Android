package com.sopt.soptkathon.android.bckk.data.api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    @SerialName("dislikeCount")
    val dislikeCount: Int = 0,
    @SerialName("nickname")
    val nickname: String = "",
    @SerialName("postList")
    val postList: List<PostDto> = listOf(),
    @SerialName("ssaId")
    val ssaId: String = "",
    @SerialName("ticketCount")
    val ticketCount: Int = 0,
) {
    @Serializable
    data class PostDto(
        @SerialName("createdDateTime")
        val createdDateTime: String = "",
        @SerialName("postContent")
        val postContent: String = "",
        @SerialName("postDislikeCount")
        val postDislikeCount: Int = 0,
        @SerialName("postId")
        val postId: Long = 0,
        @SerialName("postImg")
        val postImg: String = "",
    )
}
