package org.sopt.dateroad.domain.usecase

import javax.inject.Inject
import org.sopt.dateroad.domain.model.Timeline
import org.sopt.dateroad.domain.repository.TimelineRepository
import org.sopt.dateroad.domain.type.TimelineTimeType

class GetTimelinesUseCase @Inject constructor(
    private val timelineRepository: TimelineRepository
) {
    suspend operator fun invoke(timelineTimeType: TimelineTimeType): Result<List<Timeline>> = timelineRepository.getTimelines(timelineTimeType = timelineTimeType)
}
