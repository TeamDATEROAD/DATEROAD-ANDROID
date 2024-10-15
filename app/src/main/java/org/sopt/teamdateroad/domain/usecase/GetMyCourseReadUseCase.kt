package org.sopt.teamdateroad.domain.usecase

import javax.inject.Inject
import javax.inject.Singleton
import org.sopt.teamdateroad.domain.model.Course
import org.sopt.teamdateroad.domain.repository.MyCourseRepository

@Singleton
class GetMyCourseReadUseCase @Inject constructor(
    private val myCourseRepository: MyCourseRepository
) {
    suspend operator fun invoke(): Result<List<Course>> = myCourseRepository.getMyCourseRead()
}
