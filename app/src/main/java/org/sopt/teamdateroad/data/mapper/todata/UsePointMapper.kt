package org.sopt.teamdateroad.data.mapper.todata

import org.sopt.teamdateroad.data.dataremote.model.request.RequestUsePointDto
import org.sopt.teamdateroad.domain.model.UsePoint

fun UsePoint.toData(): RequestUsePointDto = RequestUsePointDto(
    point = this.point,
    type = this.type,
    description = this.description
)
