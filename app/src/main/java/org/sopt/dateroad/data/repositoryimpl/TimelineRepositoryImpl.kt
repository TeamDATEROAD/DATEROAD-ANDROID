package org.sopt.dateroad.data.repositoryimpl

import javax.inject.Inject
import org.sopt.dateroad.data.dataremote.datasource.TimelineRemoteDataSource
import org.sopt.dateroad.data.mapper.todata.toTimelineData
import org.sopt.dateroad.data.mapper.todomain.toDomain
import org.sopt.dateroad.data.mapper.todomain.toFutureTimelineDomain
import org.sopt.dateroad.data.mapper.todomain.toPastTimelineDomain
import org.sopt.dateroad.domain.model.Enroll
import org.sopt.dateroad.domain.model.NearestTimeline
import org.sopt.dateroad.domain.model.Timeline
import org.sopt.dateroad.domain.model.TimelineDetail
import org.sopt.dateroad.domain.repository.TimelineRepository
import org.sopt.dateroad.domain.type.TimelineTimeType

class TimelineRepositoryImpl @Inject constructor(
    private val timelineRemoteDataSource: TimelineRemoteDataSource
) : TimelineRepository {
    override suspend fun deleteTimeline(timelineId: Int) {
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

    override suspend fun postTimeline(enroll: Enroll) = runCatching {
        timelineRemoteDataSource.postTimeline(requestTimelineDto = enroll.toTimelineData())
    }
}
