package org.sopt.dateroad.data.mapper.todata

import org.sopt.dateroad.data.dataremote.model.request.RequestSignInDto
import org.sopt.dateroad.domain.model.SignIn

fun SignIn.toData(): RequestSignInDto = RequestSignInDto(
    platform = this.platform
)
