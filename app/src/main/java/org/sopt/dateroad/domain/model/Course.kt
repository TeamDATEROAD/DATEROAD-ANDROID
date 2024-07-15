package org.sopt.dateroad.domain.model

data class Course(
    val courseId: Int,
    val thumbnail: String,
    val title: String,
    val city: String,
    val cost: String,
    val duration: String,
    val like: String
)
