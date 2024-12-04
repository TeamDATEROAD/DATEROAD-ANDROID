package org.sopt.teamdateroad.domain.model

import org.sopt.teamdateroad.domain.type.RegionType

data class Enroll(
    val images: List<String> = listOf(),
    val title: String = "",
    val date: String = "",
    val startAt: String = "",
    val tags: List<String> = listOf(),
    val country: RegionType? = null,
    val city: Any? = null,
    val places: List<Place> = listOf(),
    val description: String = "",
    val cost: String = ""
)
