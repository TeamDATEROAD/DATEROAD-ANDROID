package org.sopt.teamdateroad.data.mapper.todomain

import org.sopt.teamdateroad.data.dataremote.model.response.ResponseTagDto
import org.sopt.teamdateroad.presentation.type.DateTagType

fun ResponseTagDto.toDomain(): DateTagType = DateTagType.valueOf(this.tag)
