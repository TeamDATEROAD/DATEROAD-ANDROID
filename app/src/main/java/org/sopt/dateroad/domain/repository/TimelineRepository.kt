package org.sopt.dateroad.domain.repository

import org.sopt.dateroad.domain.model.Enroll
import org.sopt.dateroad.domain.model.MainTimeline
import org.sopt.dateroad.domain.model.Timeline
import org.sopt.dateroad.domain.model.TimelineDetail
import org.sopt.dateroad.domain.type.TimelineTimeType

interface TimelineRepository {
    suspend fun deleteTimeline(timelineId: Int)

    suspend fun getTimelineDetail(timelineId: Int): TimelineDetail

    suspend fun getTimelines(time: TimelineTimeType): List<Timeline>

    suspend fun getNearestTimeline(): MainTimeline

    suspend fun postTimeline(timeline: Enroll): Result<Unit>
}
