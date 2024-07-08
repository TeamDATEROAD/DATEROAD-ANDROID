package org.sopt.dateroad.domain.model

data class Course(
    val id: Int,
    val url: String,
    val openedAt: String,
    val city: String,
    val title: String,
    val cost: String,
    val duration: String,
    val like: String,
)
