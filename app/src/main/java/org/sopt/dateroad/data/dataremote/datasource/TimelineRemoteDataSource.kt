package org.sopt.dateroad.data.dataremote.datasource

import org.sopt.dateroad.data.dataremote.model.request.RequestTimelineDto
import org.sopt.dateroad.data.dataremote.model.response.ResponseNearestTimelineDto
import org.sopt.dateroad.data.dataremote.model.response.ResponseTimelineDetailDto
import org.sopt.dateroad.data.dataremote.model.response.ResponseTimelinesDto

interface TimelineRemoteDataSource {
    suspend fun deleteTimeline(timelineId: Int)

    suspend fun getTimelineDetail(timelineId: Int): ResponseTimelineDetailDto

    suspend fun getTimelines(time: String): ResponseTimelinesDto

    suspend fun getNearestTimeline(): ResponseNearestTimelineDto

    suspend fun postTimeline(requestTimelineDto: RequestTimelineDto)
}
