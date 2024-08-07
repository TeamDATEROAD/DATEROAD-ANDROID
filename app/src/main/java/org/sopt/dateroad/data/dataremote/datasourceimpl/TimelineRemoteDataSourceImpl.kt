package org.sopt.dateroad.data.dataremote.datasourceimpl

import javax.inject.Inject
import org.sopt.dateroad.data.dataremote.datasource.TimelineRemoteDataSource
import org.sopt.dateroad.data.dataremote.model.request.RequestTimelineDto
import org.sopt.dateroad.data.dataremote.model.response.ResponseNearestTimelineDto
import org.sopt.dateroad.data.dataremote.model.response.ResponseTimelineDetailDto
import org.sopt.dateroad.data.dataremote.model.response.ResponseTimelinesDto
import org.sopt.dateroad.data.dataremote.service.TimelineService

class TimelineRemoteDataSourceImpl @Inject constructor(
    private val timelineService: TimelineService
) : TimelineRemoteDataSource {
    override suspend fun deleteTimeline(dateId: Int) = timelineService.deleteTimeline(dateId)

    override suspend fun getTimelineDetail(dateId: Int): ResponseTimelineDetailDto = timelineService.getTimelineDetail(dateId)

    override suspend fun getTimelines(time: String): ResponseTimelinesDto = timelineService.getTimelines(time)

    override suspend fun getNearestTimeline(): ResponseNearestTimelineDto = timelineService.getNearestTimeline()

    override suspend fun postTimeline(requestTimelineDto: RequestTimelineDto) = timelineService.postTimeline(requestTimelineDto = requestTimelineDto)
}
