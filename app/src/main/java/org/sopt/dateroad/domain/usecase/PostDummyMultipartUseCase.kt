package org.sopt.dateroad.domain.usecase

import org.sopt.dateroad.domain.repository.DummyRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PostDummyMultipartUseCase @Inject constructor(
    private val dummyRepository: DummyRepository
) {
    suspend operator fun invoke(image: String, content: String) =
        dummyRepository.postDummyMultipart(image = image, content = content)
}