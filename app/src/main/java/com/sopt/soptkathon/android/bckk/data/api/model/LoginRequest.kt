package com.sopt.soptkathon.android.bckk.data.api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(
    @SerialName("ssaID")
    val ssaID: String,
)
