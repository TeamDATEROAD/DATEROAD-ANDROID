package org.sopt.dateroad.data.mapper.todomain

import org.sopt.dateroad.data.dataremote.model.response.ResponseDummiesDto
import org.sopt.dateroad.domain.model.DummyModel

fun ResponseDummiesDto.ResponseDummyDto.toDomain(): DummyModel = DummyModel(
    id = this.id,
    email = this.email
)