package org.sopt.dateroad.domain.repository

import org.sopt.dateroad.domain.model.Date
import org.sopt.dateroad.domain.model.DateDetail
import org.sopt.dateroad.domain.model.MainDate
import org.sopt.dateroad.domain.type.DateTimeType
import org.sopt.dateroad.domain.model.Enroll

interface DateRepository {
    suspend fun deleteDate(dateId: Int)

    suspend fun getDateDetail(dateId: Int): DateDetail

    suspend fun getDates(time: DateTimeType): List<Date>

    suspend fun getNearestDate(): MainDate

    suspend fun postDate(date: Enroll): Result<Unit>
}
