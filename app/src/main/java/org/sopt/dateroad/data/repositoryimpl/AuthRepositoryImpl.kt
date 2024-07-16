package org.sopt.dateroad.data.repositoryimpl

import javax.inject.Inject
import org.sopt.dateroad.data.dataremote.datasource.AuthRemoteDataSource
import org.sopt.dateroad.domain.repository.AuthRepository

class AuthRepositoryImpl @Inject constructor(
    private val authRemoteDataSource: AuthRemoteDataSource
) : AuthRepository {
    override suspend fun getNicknameCheck(name: String) {
        return authRemoteDataSource.getNicknameCheck(name = name)
    }
}
