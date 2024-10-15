package org.sopt.teamdateroad.data.mapper.todata

import org.sopt.teamdateroad.data.dataremote.model.request.RequestSignInDto
import org.sopt.teamdateroad.domain.model.SignIn

fun SignIn.toData(): RequestSignInDto = RequestSignInDto(
    platform = this.platform
)
