package org.sopt.dateroad.domain.usecase

import javax.inject.Inject
import javax.inject.Singleton
import org.sopt.dateroad.domain.model.Dummy
import org.sopt.dateroad.domain.repository.UserInfoRepository

@Singleton
class GetAccessTokenUseCase @Inject constructor(
    private val userInfoRepository: UserInfoRepository
) {
    operator fun invoke() =
        userInfoRepository.getAccessToken()
}
