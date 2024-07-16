package org.sopt.dateroad.domain.repository

import org.sopt.dateroad.data.dataremote.model.response.ResponseDateDetailDto
import org.sopt.dateroad.data.dataremote.model.response.ResponseDatesDto

interface DateRepository {
    suspend fun deleteDate(dateId: Long)
    suspend fun getDateDetail(dateId: Long): ResponseDateDetailDto
    suspend fun getDates(time: String): ResponseDatesDto
}
