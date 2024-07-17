package org.sopt.dateroad.data.mapper.todomain

import org.sopt.dateroad.data.dataremote.model.response.ResponseDateDto
import org.sopt.dateroad.data.dataremote.util.Date as DateUtil
import org.sopt.dateroad.domain.model.Date
import org.sopt.dateroad.domain.type.MonthType

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
    date = this.date.toDates(),
    city = this.city.toAreaTitle(),
    tags = this.tags.map { it.toDomain() }
)

fun String.toFormattedDate(): String = String.format(
    DateUtil.TIMELINE_OUTPUT_FORMAT,
    MonthType.fromNumber(this.split(".")[1].toInt()),
    this.split(".")[2].toInt()
)
