package org.sopt.dateroad.domain.model

import org.sopt.dateroad.presentation.type.DateTagType

data class DateDetail(
    val dateId: Int = 0,
    val title: String = "",
    val startAt: String = "",
    val city: String = "",
    val tags: List<DateTagType> = emptyList(),
    val date: String = "",
    val dDay: String = "",
    val places: List<Place> = emptyList()
)
