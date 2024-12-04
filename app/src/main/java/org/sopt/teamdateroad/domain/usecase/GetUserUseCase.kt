package org.sopt.teamdateroad.domain.usecase

import javax.inject.Inject
import javax.inject.Singleton
import org.sopt.teamdateroad.domain.model.Profile
import org.sopt.teamdateroad.domain.repository.ProfileRepository

@Singleton
class GetUserUseCase @Inject constructor(
    private val profileRepository: ProfileRepository
) {
    suspend operator fun invoke(): Result<Profile> = profileRepository.getUsers()
}
