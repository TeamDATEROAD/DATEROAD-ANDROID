package org.sopt.dateroad.domain.usecase

import org.sopt.dateroad.domain.repository.UserInfoRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SetIsLoginUseCase @Inject constructor(
    private val userInfoRepository: UserInfoRepository
) {
    operator fun invoke(isLogin: Boolean) = userInfoRepository.setIsLogin(isLogin = isLogin)
}
