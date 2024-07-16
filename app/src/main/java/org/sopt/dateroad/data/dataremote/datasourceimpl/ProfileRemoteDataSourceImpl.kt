package org.sopt.dateroad.data.dataremote.datasourceimpl

import android.content.ContentResolver
import javax.inject.Inject
import org.sopt.dateroad.data.dataremote.datasource.ProfileRemoteDataSource
import org.sopt.dateroad.data.dataremote.model.response.ResponseUserDto
import org.sopt.dateroad.data.dataremote.service.ProfileService

class ProfileRemoteDataSourceImpl @Inject constructor(
    private val contentResolver: ContentResolver,
    private val profileService: ProfileService
) : ProfileRemoteDataSource {

    override suspend fun getUsers(userId: Int): ResponseUserDto =
        profileService.getUsers(userId = userId)
}
