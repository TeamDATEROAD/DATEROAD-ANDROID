package org.sopt.dateroad.data.dataremote.datasource

interface DateRemoteDataSource {
    suspend fun deleteDate(dateId: Long)
}
