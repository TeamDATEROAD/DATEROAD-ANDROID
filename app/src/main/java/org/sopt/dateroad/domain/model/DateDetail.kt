package org.sopt.dateroad.domain.model

import org.sopt.dateroad.presentation.type.DateTagType

data class DateDetail(
    val dateId: Int,
    val title: String,
    val startAt: String,
    val city: String,
    val tags: List<DateTagType>,
    val date: String,
    val dday: String,
    val places: List<Place>
)
