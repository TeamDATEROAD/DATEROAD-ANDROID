package org.sopt.dateroad.domain.model

data class Profile(
    val name: String = "",
    val tag: List<String> = listOf(),
    val point: Int = 0,
    val imageUrl: String? = null
)
