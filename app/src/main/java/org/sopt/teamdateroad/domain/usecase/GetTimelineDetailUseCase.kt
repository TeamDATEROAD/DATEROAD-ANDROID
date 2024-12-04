package org.sopt.teamdateroad.domain.usecase

import javax.inject.Inject
import org.sopt.teamdateroad.domain.model.TimelineDetail
import org.sopt.teamdateroad.domain.repository.TimelineRepository

class GetTimelineDetailUseCase @Inject constructor(
    private val timelineRepository: TimelineRepository
) {
    suspend operator fun invoke(timelineId: Int): Result<TimelineDetail> = timelineRepository.getTimelineDetail(timelineId = timelineId)
}
