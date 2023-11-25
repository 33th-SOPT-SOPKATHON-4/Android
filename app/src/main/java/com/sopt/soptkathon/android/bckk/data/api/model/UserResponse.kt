package com.sopt.soptkathon.android.bckk.data.api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    @SerialName("userDto")
    val userDto: UserDto = UserDto(),
) {
    @Serializable
    data class UserDto(
        @SerialName("dislikeReactionCount")
        val dislikeReactionCount: Int = 0,
        @SerialName("nickName")
        val nickName: String = "",
        @SerialName("postListDto")
        val postListDto: List<PostDto> = listOf(),
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
            @SerialName("postDislikeReactionCount")
            val postDislikeReactionCount: Int = 0,
            @SerialName("postId")
            val postId: String = "",
            @SerialName("postImg")
            val postImg: String = "",
        )
    }
}
