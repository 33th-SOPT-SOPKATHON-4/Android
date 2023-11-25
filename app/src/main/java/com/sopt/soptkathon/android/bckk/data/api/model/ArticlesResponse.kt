package com.sopt.soptkathon.android.bckk.data.api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArticlesResponse(
    @SerialName("postListDto")
    val postListDto: List<PostDto> = listOf(),
) {
    @Serializable
    data class PostDto(
        @SerialName("createdDateTime")
        val createdDateTime: String = "",
        @SerialName("postContent")
        val postContent: String = "",
        @SerialName("postDislikeReactionCount")
        val postDislikeReactionCount: Int = 0,
        @SerialName("postId")
        val postId: String = "",
        @SerialName("postImg")
        val postImg: String = "",
    )
}
