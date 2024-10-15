package org.sopt.teamdateroad.domain.model

data class AdvertisementDetail(
    val images: List<String> = listOf(),
    val title: String = "",
    val createAt: String = "",
    val description: String = "",
    val advertisementTagTitle: String = ""
)
