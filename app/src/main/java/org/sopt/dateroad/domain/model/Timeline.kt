package org.sopt.dateroad.domain.model

import org.sopt.dateroad.presentation.type.DateTagType

data class Timeline(
    val dateId: Int = 0,
    val dDay: String = "",
    val title: String = "",
    val date: String = "",
    val city: String = "",
    val tags: List<DateTagType> = listOf()
)
