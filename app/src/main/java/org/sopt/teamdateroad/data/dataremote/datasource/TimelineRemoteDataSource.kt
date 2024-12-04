package org.sopt.teamdateroad.data.dataremote.datasource

import org.sopt.teamdateroad.data.dataremote.model.request.RequestTimelineDto
import org.sopt.teamdateroad.data.dataremote.model.response.ResponseEnrollTimelineDto
import org.sopt.teamdateroad.data.dataremote.model.response.ResponseNearestTimelineDto
import org.sopt.teamdateroad.data.dataremote.model.response.ResponseTimelineDetailDto
import org.sopt.teamdateroad.data.dataremote.model.response.ResponseTimelinesDto

interface TimelineRemoteDataSource {
    suspend fun deleteTimeline(timelineId: Int)

    suspend fun getTimelineDetail(timelineId: Int): ResponseTimelineDetailDto

    suspend fun getTimelines(timelineTimeType: String): ResponseTimelinesDto

    suspend fun getNearestTimeline(): ResponseNearestTimelineDto

    suspend fun postTimeline(requestTimelineDto: RequestTimelineDto): ResponseEnrollTimelineDto
}
