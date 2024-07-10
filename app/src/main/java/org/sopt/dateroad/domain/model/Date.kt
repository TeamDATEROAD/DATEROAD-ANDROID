package org.sopt.dateroad.domain.model

import org.sopt.dateroad.presentation.type.DateTagType

data class Date(
    val dateId: Int,
    val title: String,
    val date: String,
    val city: String,
    val tags: List<DateTagType>,
    val dDay: String,
)
