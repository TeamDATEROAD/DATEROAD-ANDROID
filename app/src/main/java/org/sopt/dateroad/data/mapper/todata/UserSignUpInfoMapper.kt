package org.sopt.dateroad.data.mapper.todata

import org.sopt.dateroad.data.dataremote.model.request.RequestUserSignUpInfoDto
import org.sopt.dateroad.domain.model.UserSignUpInfo

fun UserSignUpInfo.toData(): RequestUserSignUpInfoDto = RequestUserSignUpInfoDto(
    name = this.name,
    platform = this.platform
)
