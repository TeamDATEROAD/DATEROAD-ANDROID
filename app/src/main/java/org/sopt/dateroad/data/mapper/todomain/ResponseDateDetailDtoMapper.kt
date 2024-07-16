package org.sopt.dateroad.data.mapper.todomain

import org.sopt.dateroad.data.dataremote.model.response.ResponseDateDetailDto
import org.sopt.dateroad.domain.model.DateDetail

fun ResponseDateDetailDto.toDomain(): DateDetail = DateDetail(
    dateId = this.dateId,
    title = this.title,
    startAt = this.startAt,
    city = this.city,
    tags = this.tags.map { it.toDomain() },
    date = this.date,
    places = this.places.map { it.toDomain() },
    dDay = this.dDay.toString()
)
