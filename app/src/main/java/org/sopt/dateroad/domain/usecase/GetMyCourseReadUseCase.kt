package org.sopt.dateroad.domain.usecase

import javax.inject.Inject
import javax.inject.Singleton
import org.sopt.dateroad.domain.model.Course
import org.sopt.dateroad.domain.repository.MyCourseRepository

@Singleton
class GetMyCourseReadUseCase @Inject constructor(
    private val myCourseRepository: MyCourseRepository
) {
    suspend operator fun invoke(): Result<List<Course>> = myCourseRepository.getMyCourseRead()
}
