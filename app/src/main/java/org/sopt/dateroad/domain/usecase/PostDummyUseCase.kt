package org.sopt.dateroad.domain.usecase

import javax.inject.Inject
import javax.inject.Singleton
import org.sopt.dateroad.domain.model.Dummy
import org.sopt.dateroad.domain.repository.DummyRepository

@Singleton
class PostDummyUseCase @Inject constructor(
    private val dummyRepository: DummyRepository
) {
    suspend operator fun invoke(dummy: Dummy): Result<Unit> =
        dummyRepository.postDummy(dummy = dummy)
}
