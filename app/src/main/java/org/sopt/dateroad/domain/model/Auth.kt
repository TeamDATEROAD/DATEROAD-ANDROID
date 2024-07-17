package org.sopt.dateroad.domain.model

import org.sopt.dateroad.domain.type.TransactionType

data class Auth(
    val accessToken:String,
    val refreshToken:String
)
