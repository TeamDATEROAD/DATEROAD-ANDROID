package org.sopt.dateroad.domain.usecase

import org.sopt.dateroad.domain.repository.UserInfoRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ClearUserInfoUseCase @Inject constructor(
    private val userInfoRepository: UserInfoRepository
) {
    operator fun invoke() = userInfoRepository.clearUserInfo()
}