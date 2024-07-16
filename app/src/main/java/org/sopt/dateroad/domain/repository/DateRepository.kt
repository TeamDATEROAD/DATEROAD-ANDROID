package org.sopt.dateroad.domain.repository

import org.sopt.dateroad.data.dataremote.model.response.ResponseDateDetailDto
import org.sopt.dateroad.data.dataremote.model.response.ResponseDatesDto
import org.sopt.dateroad.data.dataremote.model.response.ResponseNearestDateDto

interface DateRepository {
    suspend fun deleteDate(dateId: Long)
    suspend fun getDateDetail(dateId: Long): ResponseDateDetailDto
    suspend fun getDates(time: String): ResponseDatesDto
    suspend fun getNearestDate(): ResponseNearestDateDto
}
