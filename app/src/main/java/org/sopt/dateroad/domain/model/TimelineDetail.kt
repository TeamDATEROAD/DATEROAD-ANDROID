package org.sopt.dateroad.domain.model

data class TimelineDetail(
    val timelineId: Int = 0,
    val title: String = "",
    val startAt: String = "",
    val city: String = "",
    val tags: List<String> = listOf(),
    val date: String = "",
    val dDay: String = "",
    val places: List<Place> = listOf()
)
