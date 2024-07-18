package org.sopt.dateroad.domain.model

data class Auth(
    val accessToken: String,
    val refreshToken: String
)
