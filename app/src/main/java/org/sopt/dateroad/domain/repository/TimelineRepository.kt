package org.sopt.dateroad.domain.repository

import org.sopt.dateroad.domain.model.Enroll
import org.sopt.dateroad.domain.model.MainDate
import org.sopt.dateroad.domain.model.Timeline
import org.sopt.dateroad.domain.model.TimelineDetail
import org.sopt.dateroad.domain.type.TimelineType

interface TimelineRepository {
    suspend fun deleteTimeline(dateId: Int)

    suspend fun getTimelineDetail(dateId: Int): TimelineDetail

    suspend fun getTimelines(time: TimelineType): List<Timeline>

    suspend fun getNearestTimeline(): MainDate

    suspend fun postTimeline(timeline: Enroll): Result<Unit>
}
