package org.sopt.dateroad.data.mapper.todata

import org.sopt.dateroad.data.dataremote.model.request.RequestUsePointDto
import org.sopt.dateroad.domain.model.UsePoint

fun UsePoint.toData(): RequestUsePointDto = RequestUsePointDto(
    point = this.point,
    type = this.type,
    description = this.description
)
