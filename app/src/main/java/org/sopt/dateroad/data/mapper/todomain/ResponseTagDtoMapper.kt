package org.sopt.dateroad.data.mapper.todomain

import org.sopt.dateroad.data.dataremote.model.response.ResponseTagDto
import org.sopt.dateroad.presentation.type.DateTagType

fun ResponseTagDto.toDomain(): DateTagType = DateTagType.valueOf(this.tag)
