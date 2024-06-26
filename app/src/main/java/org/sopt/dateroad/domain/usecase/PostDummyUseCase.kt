package org.sopt.dateroad.domain.usecase

import org.sopt.dateroad.domain.model.DummyModel
import org.sopt.dateroad.domain.repository.DummyRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PostDummyUseCase @Inject constructor(
    private val dummyRepository: DummyRepository
){
    suspend operator fun invoke(dummyModel: DummyModel): Result<Unit> =
        dummyRepository.postDummy(dummyModel = dummyModel)
}