package org.sopt.dateroad.domain.usecase

import javax.inject.Inject
import javax.inject.Singleton
import org.sopt.dateroad.domain.repository.UserInfoRepository

@Singleton
class SetUserIdUseCase @Inject constructor(
    private val userInfoRepository: UserInfoRepository
) {
    operator fun invoke(userId: String) = userInfoRepository.setUserId(userId = userId)
}
