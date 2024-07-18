package org.sopt.dateroad.domain.usecase

import javax.inject.Inject
import javax.inject.Singleton
import org.sopt.dateroad.domain.model.Enroll
import org.sopt.dateroad.domain.repository.DateRepository

@Singleton
class PostDateUseCase @Inject constructor(
    private val dateRepository: DateRepository
) {
    suspend operator fun invoke(date: Enroll): Result<Unit> = dateRepository.postDate(date = date)
}
