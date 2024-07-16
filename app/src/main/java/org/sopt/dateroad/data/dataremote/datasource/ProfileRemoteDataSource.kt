package org.sopt.dateroad.data.dataremote.datasource

import org.sopt.dateroad.data.dataremote.model.response.ResponseUserDto

interface ProfileRemoteDataSource {
    suspend fun getUsers(userId: Int): ResponseUserDto
}
