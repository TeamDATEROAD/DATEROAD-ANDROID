package org.sopt.teamdateroad.data.mapper.todomain

import org.sopt.teamdateroad.data.dataremote.model.response.ResponseAuthDto
import org.sopt.teamdateroad.domain.model.Auth

fun ResponseAuthDto.toDomain(): Auth = Auth(
    accessToken = this.accessToken,
    refreshToken = this.refreshToken
)
