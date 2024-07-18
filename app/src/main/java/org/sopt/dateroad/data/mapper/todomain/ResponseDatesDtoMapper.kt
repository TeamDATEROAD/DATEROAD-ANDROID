package org.sopt.dateroad.data.mapper.todomain

import org.sopt.dateroad.data.dataremote.model.response.ResponseDateDto
import org.sopt.dateroad.domain.model.Date

fun ResponseDateDto.toTimelineCardDomain(): Date = Date(
    dateId = this.dateId,
    dDay = this.dDay.toDDayString(),
    title = this.title,
    date = this.date.toFormattedDate(),
    city = this.city.toAreaTitle(),
    tags = this.tags.map { it.toDomain() }
)

fun ResponseDateDto.toDatesDomain(): Date = Date(
    dateId = this.dateId,
    dDay = this.dDay.toDDayString(),
    title = this.title,
    date = this.date.toBasicDates(),
    city = this.city.toAreaTitle(),
    tags = this.tags.map { it.toDomain() }
)
