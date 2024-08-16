package org.sopt.dateroad.domain.repository

import org.sopt.dateroad.domain.model.Enroll
import org.sopt.dateroad.domain.model.NearestTimeline
import org.sopt.dateroad.domain.model.Timeline
import org.sopt.dateroad.domain.model.TimelineDetail
import org.sopt.dateroad.domain.type.TimelineTimeType

interface TimelineRepository {
    suspend fun deleteTimeline(timelineId: Int)

    suspend fun getTimelineDetail(timelineId: Int): Result<TimelineDetail>

    suspend fun getTimelines(timelineTimeType: TimelineTimeType): Result<List<Timeline>>

    suspend fun getNearestTimeline(): Result<NearestTimeline>

    suspend fun postTimeline(enroll: Enroll): Result<Unit>
}
