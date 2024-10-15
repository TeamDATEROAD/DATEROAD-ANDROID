package org.sopt.teamdateroad.domain.usecase

import javax.inject.Inject
import javax.inject.Singleton
import org.sopt.teamdateroad.domain.model.EditProfile
import org.sopt.teamdateroad.domain.repository.AuthRepository

@Singleton
class PatchEditProfileUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(editProfile: EditProfile): Result<Unit> = authRepository.patchEditProfile(editProfile = editProfile)
}