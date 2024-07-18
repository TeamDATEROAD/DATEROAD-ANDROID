package org.sopt.dateroad.data.mapper.todomain

import org.sopt.dateroad.data.dataremote.model.response.ResponseDateDetailDto
import org.sopt.dateroad.domain.model.DateDetail

fun ResponseDateDetailDto.toDomain(): DateDetail = DateDetail(
    dateId = this.dateId,
    title = this.title,
    startAt = this.startAt,
    city = this.city.toAreaTitle(),
    tags = this.tags.map { it.toDomain() },
    date = this.date.toBasicDates(),
    places = this.places.sortedBy { responsePlaceDto -> responsePlaceDto.sequence }.map { it.toDomain() },
    dDay = this.dDay.toDDayString()
)
