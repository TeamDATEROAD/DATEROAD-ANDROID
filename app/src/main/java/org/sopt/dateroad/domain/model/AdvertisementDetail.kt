package org.sopt.dateroad.domain.model

data class AdvertisementDetail(
    val images: List<String> = listOf(),
    val tag: String = "",
    val title: String = "",
    val createAt: String = "",
    val description: String = ""
)
