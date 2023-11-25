package com.sopt.soptkathon.android.bckk.data.api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    @SerialName("dislikeCount")
    val dislikeCount: Int = 0,
    @SerialName("nickname")
    val nickname: String = "",
    @SerialName("ssaId")
    val ssaId: String = "",
    @SerialName("ticketCount")
    val ticketCount: Int = 0,
)
