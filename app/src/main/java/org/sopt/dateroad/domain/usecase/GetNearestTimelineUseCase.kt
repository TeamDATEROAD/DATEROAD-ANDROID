package org.sopt.dateroad.domain.usecase

import javax.inject.Inject
import org.sopt.dateroad.domain.model.MainDate
import org.sopt.dateroad.domain.repository.TimelineRepository

class GetNearestTimelineUseCase @Inject constructor(
    private val timelineRepository: TimelineRepository
) {
    suspend operator fun invoke(): Result<MainDate> = runCatching {
        timelineRepository.getNearestTimeline()
    }
}
