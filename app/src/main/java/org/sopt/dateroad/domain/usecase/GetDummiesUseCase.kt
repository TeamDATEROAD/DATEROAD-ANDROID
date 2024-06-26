package org.sopt.dateroad.domain.usecase

import org.sopt.dateroad.domain.model.DummyModel
import org.sopt.dateroad.domain.repository.DummyRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetDummiesUseCase @Inject constructor(
    private val dummyRepository: DummyRepository
) {
    suspend operator fun invoke(page: Int): Result<List<DummyModel>> =
        dummyRepository.getDummies(page = page)
}