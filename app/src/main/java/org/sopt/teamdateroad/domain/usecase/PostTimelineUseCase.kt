package org.sopt.teamdateroad.domain.usecase

import javax.inject.Inject
import javax.inject.Singleton
import org.sopt.teamdateroad.domain.model.Enroll
import org.sopt.teamdateroad.domain.model.EnrollTimelineResult
import org.sopt.teamdateroad.domain.repository.TimelineRepository

@Singleton
class PostTimelineUseCase @Inject constructor(
    private val timelineRepository: TimelineRepository
) {
    suspend operator fun invoke(enroll: Enroll): Result<EnrollTimelineResult> = timelineRepository.postTimeline(enroll = enroll)
}
