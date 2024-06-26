package org.sopt.dateroad.data.mapper.todata

import org.sopt.dateroad.data.dataremote.model.request.RequestDummyDto
import org.sopt.dateroad.domain.model.DummyModel

fun DummyModel.toData() : RequestDummyDto = RequestDummyDto(
    id = this.id,
    email = this.email
)