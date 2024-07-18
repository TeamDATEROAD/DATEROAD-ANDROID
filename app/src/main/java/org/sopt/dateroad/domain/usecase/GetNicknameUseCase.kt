package org.sopt.dateroad.domain.usecase

import javax.inject.Inject
import javax.inject.Singleton
import org.sopt.dateroad.domain.repository.UserInfoRepository

@Singleton
class GetNicknameUseCase @Inject constructor(
    private val authInfoRepository: UserInfoRepository
) {
    operator fun invoke(): String = authInfoRepository.getNickname()
}
