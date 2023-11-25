package com.sopt.soptkathon.android.bckk.data.api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArticlesRequest(
    @SerialName("postImg")
    val postImg: String = "",
    @SerialName("postContent")
    val postContent: String = "",
)
