package org.sopt.teamdateroad.data.mapper.todata

import org.sopt.teamdateroad.data.dataremote.model.request.RequestUserSignUpInfoDto
import org.sopt.teamdateroad.domain.model.UserSignUpInfo

fun UserSignUpInfo.toData(): RequestUserSignUpInfoDto = RequestUserSignUpInfoDto(
    name = this.name,
    platform = this.platform
)
