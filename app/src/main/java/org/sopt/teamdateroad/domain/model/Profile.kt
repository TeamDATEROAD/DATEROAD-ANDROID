package org.sopt.teamdateroad.domain.model

data class Profile(
    val name: String = "",
    val tag: List<String> = listOf(),
    val point: String = "",
    val imageUrl: String? = null
)
