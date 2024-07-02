package org.sopt.dateroad.domain.usecase

import javax.inject.Inject
import javax.inject.Singleton
import org.sopt.dateroad.domain.repository.DummyRepository

@Singleton
class PostDummyMultipartUseCase @Inject constructor(
    private val dummyRepository: DummyRepository
) {
    suspend operator fun invoke(image: String, content: String): Result<Unit> =
        dummyRepository.postDummyMultipart(image = image, content = content)
}
