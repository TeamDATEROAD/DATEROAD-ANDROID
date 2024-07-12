package org.sopt.dateroad.domain.model

import org.sopt.dateroad.presentation.type.DateTagType

data class EditProfile(
    val name: String,
    val tag: List<DateTagType>,
    val image: String
)
