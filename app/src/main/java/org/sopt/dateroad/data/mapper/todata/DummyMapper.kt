package org.sopt.dateroad.data.mapper.todata

import org.sopt.dateroad.data.dataremote.model.request.RequestDummyDto
import org.sopt.dateroad.domain.model.Dummy

fun Dummy.toData() : RequestDummyDto = RequestDummyDto(
    id = this.id,
    email = this.email
)