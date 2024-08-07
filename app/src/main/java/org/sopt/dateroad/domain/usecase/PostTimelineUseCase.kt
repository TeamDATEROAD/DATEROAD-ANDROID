package org.sopt.dateroad.domain.usecase

import javax.inject.Inject
import javax.inject.Singleton
import org.sopt.dateroad.domain.model.Enroll
import org.sopt.dateroad.domain.repository.TimelineRepository

@Singleton
class PostTimelineUseCase @Inject constructor(
    private val timelineRepository: TimelineRepository
) {
    suspend operator fun invoke(timeline: Enroll): Result<Unit> = timelineRepository.postTimeline(timeline = timeline)
}
