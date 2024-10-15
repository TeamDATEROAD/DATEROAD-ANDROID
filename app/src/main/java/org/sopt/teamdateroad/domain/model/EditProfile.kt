package org.sopt.teamdateroad.domain.model

data class EditProfile(
    val name: String = "",
    val tags: List<String> = listOf(),
    val image: String? = ""
)