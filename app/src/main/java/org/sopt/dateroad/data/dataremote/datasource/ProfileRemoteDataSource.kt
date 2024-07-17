package org.sopt.dateroad.data.dataremote.datasource

import org.sopt.dateroad.data.dataremote.model.response.ResponseProfileDto

interface ProfileRemoteDataSource {
    suspend fun getProfile(): ResponseProfileDto
}
