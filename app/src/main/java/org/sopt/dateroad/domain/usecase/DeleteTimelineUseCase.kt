package org.sopt.dateroad.domain.usecase

import javax.inject.Inject
import javax.inject.Singleton
import org.sopt.dateroad.domain.repository.TimelineRepository

@Singleton
class DeleteTimelineUseCase @Inject constructor(
    private val timelineRepository: TimelineRepository
) {
    suspend operator fun invoke(timelineId: Int): Result<Unit> = runCatching { timelineRepository.deleteTimeline(timelineId = timelineId) }
}
