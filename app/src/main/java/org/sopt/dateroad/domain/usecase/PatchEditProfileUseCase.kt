package org.sopt.dateroad.domain.usecase

import javax.inject.Inject
import javax.inject.Singleton
import org.sopt.dateroad.domain.model.EditProfile
import org.sopt.dateroad.domain.repository.AuthRepository

@Singleton
class PatchEditProfileUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(editProfile: EditProfile): Result<Unit> =
        authRepository.patchEditProfile(editProfile = editProfile)
}
