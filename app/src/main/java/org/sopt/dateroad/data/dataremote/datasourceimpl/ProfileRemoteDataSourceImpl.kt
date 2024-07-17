package org.sopt.dateroad.data.dataremote.datasourceimpl

import javax.inject.Inject
import org.sopt.dateroad.data.dataremote.datasource.ProfileRemoteDataSource
import org.sopt.dateroad.data.dataremote.model.response.ResponseProfileDto
import org.sopt.dateroad.data.dataremote.service.ProfileService

class ProfileRemoteDataSourceImpl @Inject constructor(
    private val profileService: ProfileService
) : ProfileRemoteDataSource {

    override suspend fun getProfile(): ResponseProfileDto =
        profileService.getProfile()
}
