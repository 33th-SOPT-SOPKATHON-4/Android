package com.sopt.soptkathon.android.bckk.data.api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PostDisLikeRequest(
    @SerialName("postIdList")
    val postIdList: List<Long>,
)
