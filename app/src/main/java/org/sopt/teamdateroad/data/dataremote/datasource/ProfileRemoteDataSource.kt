package org.sopt.teamdateroad.data.dataremote.datasource

import org.sopt.teamdateroad.data.dataremote.model.response.ResponseProfileDto

interface ProfileRemoteDataSource {
    suspend fun getProfile(): ResponseProfileDto
}
