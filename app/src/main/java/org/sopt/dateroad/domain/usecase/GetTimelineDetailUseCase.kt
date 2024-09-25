package org.sopt.dateroad.domain.usecase

import javax.inject.Inject
import org.sopt.dateroad.domain.model.TimelineDetail
import org.sopt.dateroad.domain.repository.TimelineRepository

class GetTimelineDetailUseCase @Inject constructor(
    private val timelineRepository: TimelineRepository
) {
    suspend operator fun invoke(timelineId: Int): Result<TimelineDetail> = timelineRepository.getTimelineDetail(timelineId = timelineId)
}
