package org.sopt.dateroad.domain.repository

import org.sopt.dateroad.data.dataremote.model.response.ResponseDateDetailDto

interface DateRepository {
    suspend fun deleteDate(dateId: Long)
    suspend fun getDateDetail(dateId: Long): ResponseDateDetailDto
}
