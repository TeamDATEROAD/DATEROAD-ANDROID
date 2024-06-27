package org.sopt.dateroad.domain.usecase

import org.sopt.dateroad.domain.repository.UserInfoRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetIsLoginUseCase @Inject constructor(
    private val userInfoRepository: UserInfoRepository
) {
    operator fun invoke(): Boolean = userInfoRepository.getIsLogin()
}
