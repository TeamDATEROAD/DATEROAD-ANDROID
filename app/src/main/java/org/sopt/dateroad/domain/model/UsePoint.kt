package org.sopt.dateroad.domain.model

import org.sopt.dateroad.domain.type.TransactionType

data class UsePoint(
    val point: Int,
    val type: String,
    val description: String
)
