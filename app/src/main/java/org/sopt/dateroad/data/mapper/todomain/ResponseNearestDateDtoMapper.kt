package org.sopt.dateroad.data.mapper.todomain

import org.sopt.dateroad.data.dataremote.model.response.ResponseNearestDateDto
import org.sopt.dateroad.domain.model.MainDate

fun ResponseNearestDateDto.toDomain(): MainDate = MainDate(
    dateId = this.dateId,
    dDay = this.dDay.toDDayString(),
    dateName = this.dateName,
    date = toFormattedDate(),
    startAt = this.startAt.toStartAtString()
)
