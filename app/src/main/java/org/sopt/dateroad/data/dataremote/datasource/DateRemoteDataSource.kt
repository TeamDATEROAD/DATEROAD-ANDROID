package org.sopt.dateroad.data.dataremote.datasource

import org.sopt.dateroad.data.dataremote.model.request.RequestDateDto
import org.sopt.dateroad.data.dataremote.model.response.ResponseDateDetailDto
import org.sopt.dateroad.data.dataremote.model.response.ResponseDatesDto
import org.sopt.dateroad.data.dataremote.model.response.ResponseNearestDateDto

interface DateRemoteDataSource {
    suspend fun deleteDate(dateId: Int)

    suspend fun getDateDetail(dateId: Int): ResponseDateDetailDto

    suspend fun getDates(time: String): ResponseDatesDto

    suspend fun getNearestDate(): ResponseNearestDateDto

    suspend fun postDate(requestDateDto: RequestDateDto)
}
