package org.sopt.dateroad.data.dataremote.datasource

import org.sopt.dateroad.data.dataremote.model.request.RequestDateDto

interface DateRemoteDataSource {
    suspend fun postDate(requestDateDto: RequestDateDto)
}
