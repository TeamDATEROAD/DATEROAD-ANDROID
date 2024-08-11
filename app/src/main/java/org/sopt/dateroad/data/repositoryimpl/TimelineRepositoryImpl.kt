package org.sopt.dateroad.data.repositoryimpl

import javax.inject.Inject
import org.sopt.dateroad.data.dataremote.datasource.TimelineRemoteDataSource
import org.sopt.dateroad.data.mapper.todata.toTimelineData
import org.sopt.dateroad.data.mapper.todomain.toDomain
import org.sopt.dateroad.data.mapper.todomain.toTimelineCardDomain
import org.sopt.dateroad.data.mapper.todomain.toTimelinesDomain
import org.sopt.dateroad.domain.model.Enroll
import org.sopt.dateroad.domain.model.MainTimeline
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

    override suspend fun getTimelineDetail(timelineId: Int): TimelineDetail = timelineRemoteDataSource.getTimelineDetail(timelineId).toDomain()

    override suspend fun getTimelines(time: TimelineTimeType): List<Timeline> = timelineRemoteDataSource.getTimelines(time.name).timelines.map {
        when (time) {
            TimelineTimeType.PAST -> it.toTimelinesDomain()
            TimelineTimeType.FUTURE -> it.toTimelineCardDomain()
        }
    }

    override suspend fun getNearestTimeline(): MainTimeline = timelineRemoteDataSource.getNearestTimeline().toDomain()

    override suspend fun postTimeline(timeline: Enroll) = runCatching {
        timelineRemoteDataSource.postTimeline(requestTimelineDto = timeline.toTimelineData())
    }
}
