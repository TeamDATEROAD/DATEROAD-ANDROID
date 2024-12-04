package org.sopt.teamdateroad.domain.repository

import org.sopt.teamdateroad.domain.model.Enroll
import org.sopt.teamdateroad.domain.model.EnrollTimelineResult
import org.sopt.teamdateroad.domain.model.NearestTimeline
import org.sopt.teamdateroad.domain.model.Timeline
import org.sopt.teamdateroad.domain.model.TimelineDetail
import org.sopt.teamdateroad.domain.type.TimelineTimeType

interface TimelineRepository {
    suspend fun deleteTimeline(timelineId: Int): Result<Unit>

    suspend fun getTimelineDetail(timelineId: Int): Result<TimelineDetail>

    suspend fun getTimelines(timelineTimeType: TimelineTimeType): Result<List<Timeline>>

    suspend fun getNearestTimeline(): Result<NearestTimeline>

    suspend fun postTimeline(enroll: Enroll): Result<EnrollTimelineResult>
}
