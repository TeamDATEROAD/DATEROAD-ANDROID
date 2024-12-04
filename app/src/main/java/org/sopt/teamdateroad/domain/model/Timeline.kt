package org.sopt.teamdateroad.domain.model

import org.sopt.teamdateroad.presentation.type.DateTagType

data class Timeline(
    val timelineId: Int = 0,
    val dDay: String = "",
    val title: String = "",
    val date: String = "",
    val city: String = "",
    val tags: List<DateTagType> = listOf()
)
