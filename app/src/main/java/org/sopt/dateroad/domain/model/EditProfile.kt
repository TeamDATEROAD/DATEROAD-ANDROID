package org.sopt.dateroad.domain.model

data class EditProfile(
    val name: String = "",
    val tags: List<String> = listOf(),
    val image: String? = "",
    val isDefaultImage: Boolean = true
)
