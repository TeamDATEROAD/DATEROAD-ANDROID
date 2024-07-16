
package org.sopt.dateroad.data.repositoryimpl

import javax.inject.Inject
import org.sopt.dateroad.data.dataremote.datasource.ProfileRemoteDataSource
import org.sopt.dateroad.data.mapper.todomain.toDomain
import org.sopt.dateroad.domain.model.Profile
import org.sopt.dateroad.domain.repository.ProfileRepository

class ProfileRepositoryImpl @Inject constructor(
    private val profileDataSource: ProfileRemoteDataSource
) : ProfileRepository {
    override suspend fun getUsers(userId: Int): Result<Profile> = runCatching {
        profileDataSource.getUsers(userId = userId).toDomain()
    }
}
