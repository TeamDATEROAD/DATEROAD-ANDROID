package org.sopt.teamdateroad.domain.usecase

import javax.inject.Inject
import javax.inject.Singleton
import org.sopt.teamdateroad.domain.repository.UserInfoRepository

@Singleton
class SetNicknameUseCase @Inject constructor(
    private val userInfoRepository: UserInfoRepository
) {
    operator fun invoke(nickname: String) = userInfoRepository.setNickname(nickname = nickname)
}
