package org.sopt.dateroad.data.mapper.todomain

import org.sopt.dateroad.data.dataremote.model.response.ResponseDateDto
import org.sopt.dateroad.domain.model.Date
import org.sopt.dateroad.presentation.type.DateTagType

fun ResponseDateDto.toDomain(): Date = Date(
    dateId = this.dateId,
    dDay = this.dDay.toString(),
    title = this.title,
    date = this.date,
    city = this.city!!.toAreaTitle(),
    tags = this.tags.map { DateTagType.valueOf(it.tag) }
)
