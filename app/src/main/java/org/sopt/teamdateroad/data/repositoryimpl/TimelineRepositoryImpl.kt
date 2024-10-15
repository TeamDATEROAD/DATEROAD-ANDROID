package org.sopt.teamdateroad.data.repositoryimpl

import javax.inject.Inject
import org.sopt.teamdateroad.data.dataremote.datasource.TimelineRemoteDataSource
import org.sopt.teamdateroad.data.mapper.todata.toTimelineData
import org.sopt.teamdateroad.data.mapper.todomain.toDomain
import org.sopt.teamdateroad.data.mapper.todomain.toFutureTimelineDomain
import org.sopt.teamdateroad.data.mapper.todomain.toPastTimelineDomain
import org.sopt.teamdateroad.domain.model.Enroll
import org.sopt.teamdateroad.domain.model.EnrollTimelineResult
import org.sopt.teamdateroad.domain.model.NearestTimeline
import org.sopt.teamdateroad.domain.model.Timeline
import org.sopt.teamdateroad.domain.model.TimelineDetail
import org.sopt.teamdateroad.domain.repository.TimelineRepository
import org.sopt.teamdateroad.domain.type.TimelineTimeType

class TimelineRepositoryImpl @Inject constructor(
    private val timelineRemoteDataSource: TimelineRemoteDataSource
) : TimelineRepository {
    override suspend fun deleteTimeline(timelineId: Int) = runCatching {
        timelineRemoteDataSource.deleteTimeline(timelineId)
    }

    override suspend fun getTimelineDetail(timelineId: Int): Result<TimelineDetail> = runCatching {
        timelineRemoteDataSource.getTimelineDetail(timelineId).toDomain()
    }

    override suspend fun getTimelines(timelineTimeType: TimelineTimeType): Result<List<Timeline>> = runCatching {
        timelineRemoteDataSource.getTimelines(timelineTimeType.name).timelines.map {
            when (timelineTimeType) {
                TimelineTimeType.PAST -> it.toPastTimelineDomain()
                TimelineTimeType.FUTURE -> it.toFutureTimelineDomain()
            }
        }
    }

    override suspend fun getNearestTimeline(): Result<NearestTimeline> = runCatching {
        timelineRemoteDataSource.getNearestTimeline().toDomain()
    }

    override suspend fun postTimeline(enroll: Enroll): Result<EnrollTimelineResult> = runCatching {
        timelineRemoteDataSource.postTimeline(requestTimelineDto = enroll.toTimelineData()).toDomain()
    }
}
