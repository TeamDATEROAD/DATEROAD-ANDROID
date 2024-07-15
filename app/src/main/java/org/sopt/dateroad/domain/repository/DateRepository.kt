package org.sopt.dateroad.domain.repository

interface DateRepository {
    suspend fun deleteDate(dateId: Long)
}
