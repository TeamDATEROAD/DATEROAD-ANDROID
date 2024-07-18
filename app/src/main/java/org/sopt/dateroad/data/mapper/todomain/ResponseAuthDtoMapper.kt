package org.sopt.dateroad.data.mapper.todomain

import org.sopt.dateroad.data.dataremote.model.response.ResponseAuthDto
import org.sopt.dateroad.domain.model.Auth

fun ResponseAuthDto.toDomain(): Auth = Auth(
    accessToken = this.accessToken,
    refreshToken = this.refreshToken
)
