package org.sopt.dateroad.data.repositoryimpl

import android.content.ContentResolver
import javax.inject.Inject
import org.sopt.dateroad.data.dataremote.datasource.TimelineRemoteDataSource
import org.sopt.dateroad.data.mapper.todata.toTimelineData
import org.sopt.dateroad.data.mapper.todomain.toDomain
import org.sopt.dateroad.data.mapper.todomain.toTimelineCardDomain
import org.sopt.dateroad.data.mapper.todomain.toTimelinesDomain
import org.sopt.dateroad.domain.model.Enroll
import org.sopt.dateroad.domain.model.MainDate
import org.sopt.dateroad.domain.model.Timeline
import org.sopt.dateroad.domain.model.TimelineDetail
import org.sopt.dateroad.domain.repository.TimelineRepository
import org.sopt.dateroad.domain.type.TimelineType

class TimelineRepositoryImpl @Inject constructor(
    private val timelineRemoteDataSource: TimelineRemoteDataSource
) : TimelineRepository {
    override suspend fun deleteTimeline(dateId: Int) {
        timelineRemoteDataSource.deleteTimeline(dateId)
    }

    override suspend fun getTimelineDetail(dateId: Int): TimelineDetail = timelineRemoteDataSource.getTimelineDetail(dateId).toDomain()

    override suspend fun getTimelines(time: TimelineType): List<Timeline> = timelineRemoteDataSource.getTimelines(time.name).timelines.map {
        when (time) {
            TimelineType.PAST -> it.toTimelinesDomain()
            TimelineType.FUTURE -> it.toTimelineCardDomain()
        }
    }

    override suspend fun getNearestTimeline(): MainDate = timelineRemoteDataSource.getNearestTimeline().toDomain()

    override suspend fun postTimeline(timeline: Enroll) = runCatching {
        timelineRemoteDataSource.postTimeline(requestTimelineDto = timeline.toTimelineData())
    }
}
