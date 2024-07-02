package org.sopt.dateroad.data.mapper.todomain

import org.sopt.dateroad.data.dataremote.model.response.ResponseDummiesDto
import org.sopt.dateroad.domain.model.Dummy

fun ResponseDummiesDto.ResponseDummyDto.toDomain(): Dummy = Dummy(
    id = this.id,
    email = this.email
)
