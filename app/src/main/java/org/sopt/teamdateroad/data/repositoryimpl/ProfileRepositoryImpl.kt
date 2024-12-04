
package org.sopt.teamdateroad.data.repositoryimpl

import javax.inject.Inject
import org.sopt.teamdateroad.data.dataremote.datasource.ProfileRemoteDataSource
import org.sopt.teamdateroad.data.mapper.todomain.toDomain
import org.sopt.teamdateroad.domain.model.Profile
import org.sopt.teamdateroad.domain.repository.ProfileRepository

class ProfileRepositoryImpl @Inject constructor(
    private val profileDataSource: ProfileRemoteDataSource
) : ProfileRepository {
    override suspend fun getUsers(): Result<Profile> = runCatching {
        profileDataSource.getProfile().toDomain()
    }
}
