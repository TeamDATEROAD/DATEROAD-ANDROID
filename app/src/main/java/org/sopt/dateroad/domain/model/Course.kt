package org.sopt.dateroad.domain.model

import org.sopt.dateroad.presentation.type.MoneyTagType

data class Course(
    val id: Int,
    val url: String,
    val city: String,
    val title: String,
    val cost: MoneyTagType,
    val duration: String,
    val like: String
)
