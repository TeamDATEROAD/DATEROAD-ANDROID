package org.sopt.dateroad.domain.usecase

import javax.inject.Inject
import javax.inject.Singleton
import org.sopt.dateroad.domain.repository.UserInfoRepository

@Singleton
class SetAccessTokenUseCase
@Inject
constructor(
    private val userInfoRepository: UserInfoRepository
) {
    operator fun invoke(accessToken: String) = userInfoRepository.setAccessToken(accessToken = accessToken)
}
