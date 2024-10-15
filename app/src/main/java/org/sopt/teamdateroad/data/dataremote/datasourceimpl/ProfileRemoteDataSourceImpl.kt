package org.sopt.teamdateroad.data.dataremote.datasourceimpl

import javax.inject.Inject
import org.sopt.teamdateroad.data.dataremote.datasource.ProfileRemoteDataSource
import org.sopt.teamdateroad.data.dataremote.model.response.ResponseProfileDto
import org.sopt.teamdateroad.data.dataremote.service.ProfileService

class ProfileRemoteDataSourceImpl @Inject constructor(
    private val profileService: ProfileService
) : ProfileRemoteDataSource {

    override suspend fun getProfile(): ResponseProfileDto =
        profileService.getProfile()
}
