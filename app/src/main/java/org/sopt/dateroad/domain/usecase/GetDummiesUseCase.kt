package org.sopt.dateroad.domain.usecase

import javax.inject.Inject
import javax.inject.Singleton
import org.sopt.dateroad.domain.model.Dummy
import org.sopt.dateroad.domain.repository.DummyRepository

@Singleton
class GetDummiesUseCase @Inject constructor(
    private val dummyRepository: DummyRepository
) {
    suspend operator fun invoke(page: Int): Result<List<Dummy>> =
        dummyRepository.getDummies(page = page)
}
