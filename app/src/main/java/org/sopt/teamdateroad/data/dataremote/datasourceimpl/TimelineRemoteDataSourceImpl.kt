package org.sopt.teamdateroad.data.dataremote.datasourceimpl

import javax.inject.Inject
import org.sopt.teamdateroad.data.dataremote.datasource.TimelineRemoteDataSource
import org.sopt.teamdateroad.data.dataremote.model.request.RequestTimelineDto
import org.sopt.teamdateroad.data.dataremote.model.response.ResponseEnrollTimelineDto
import org.sopt.teamdateroad.data.dataremote.model.response.ResponseNearestTimelineDto
import org.sopt.teamdateroad.data.dataremote.model.response.ResponseTimelineDetailDto
import org.sopt.teamdateroad.data.dataremote.model.response.ResponseTimelinesDto
import org.sopt.teamdateroad.data.dataremote.service.TimelineService

class TimelineRemoteDataSourceImpl @Inject constructor(
    private val timelineService: TimelineService
) : TimelineRemoteDataSource {
    override suspend fun deleteTimeline(timelineId: Int) = timelineService.deleteTimeline(timelineId = timelineId)

    override suspend fun getTimelineDetail(timelineId: Int): ResponseTimelineDetailDto = timelineService.getTimelineDetail(timelineId = timelineId)

    override suspend fun getTimelines(timelineTimeType: String): ResponseTimelinesDto = timelineService.getTimelines(timelineTimeType = timelineTimeType)

    override suspend fun getNearestTimeline(): ResponseNearestTimelineDto = timelineService.getNearestTimeline()

    override suspend fun postTimeline(requestTimelineDto: RequestTimelineDto): ResponseEnrollTimelineDto = timelineService.postTimeline(requestTimelineDto = requestTimelineDto)
}
