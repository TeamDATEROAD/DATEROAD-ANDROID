package org.sopt.dateroad.domain.model

import org.sopt.dateroad.presentation.type.DateTagType

data class DateDetail(
    val dateId: Long = 0,
    val title: String = "",
    val startAt: String = "",
    val city: String? = null,
    val tags: List<DateTagType> = emptyList(),
    val date: String = "",
    val dDay: String = "",
    val places: List<Place> = emptyList()
)
