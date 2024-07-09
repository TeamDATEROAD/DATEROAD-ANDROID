package org.sopt.dateroad.domain.model

data class PointHistory(
    val gained: List<Point> = listOf(),
    val used: List<Point> = listOf()
)