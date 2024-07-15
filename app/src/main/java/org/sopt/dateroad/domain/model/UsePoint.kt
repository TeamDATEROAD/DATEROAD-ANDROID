package org.sopt.dateroad.domain.model

import org.sopt.dateroad.domain.type.TransactionType

data class UsePoint(
    val point: Int,
    val transactionType: TransactionType,
    val description: String
)
