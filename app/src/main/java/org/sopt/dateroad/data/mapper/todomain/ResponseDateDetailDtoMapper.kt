package org.sopt.dateroad.data.mapper.todomain

import org.sopt.dateroad.data.dataremote.model.response.ResponseDateDetailDto
import org.sopt.dateroad.data.mapper.toEntity.toBasicDates
import org.sopt.dateroad.data.mapper.toEntity.toDDayString
import org.sopt.dateroad.domain.model.DateDetail

fun ResponseDateDetailDto.toDomain(): DateDetail = DateDetail(
    dateId = this.dateId,
    title = this.title,
    startAt = this.startAt,
    city = this.city,
    tags = this.tags.map { responseTagDto -> responseTagDto.tag },
    date = this.date.toBasicDates(),
    places = this.places.sortedBy { responsePlaceDto -> responsePlaceDto.sequence }.map { it.toDomain() },
    dDay = this.dDay.toDDayString()
)
