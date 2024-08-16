package org.sopt.dateroad.data.dataremote.service

import org.sopt.dateroad.data.dataremote.model.request.RequestTimelineDto
import org.sopt.dateroad.data.dataremote.model.response.ResponseNearestTimelineDto
import org.sopt.dateroad.data.dataremote.model.response.ResponseTimelineDetailDto
import org.sopt.dateroad.data.dataremote.model.response.ResponseTimelinesDto
import org.sopt.dateroad.data.dataremote.util.ApiConstraints.API
import org.sopt.dateroad.data.dataremote.util.ApiConstraints.DATES
import org.sopt.dateroad.data.dataremote.util.ApiConstraints.DATE_ID
import org.sopt.dateroad.data.dataremote.util.ApiConstraints.NEAREST
import org.sopt.dateroad.data.dataremote.util.ApiConstraints.TIME
import org.sopt.dateroad.data.dataremote.util.ApiConstraints.VERSION
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface TimelineService {
    @DELETE("$API/$VERSION/$DATES/{$DATE_ID}")
    suspend fun deleteTimeline(
        @Path(DATE_ID) timelineId: Int
    )

    @GET("$API/$VERSION/$DATES/{$DATE_ID}")
    suspend fun getTimelineDetail(
        @Path(DATE_ID) timelineId: Int
    ): ResponseTimelineDetailDto

    @GET("$API/$VERSION/$DATES")
    suspend fun getTimelines(
        @Query(TIME) timelineTimeType: String
    ): ResponseTimelinesDto

    @GET("$API/$VERSION/$DATES/$NEAREST")
    suspend fun getNearestTimeline(): ResponseNearestTimelineDto

    @POST("$API/$VERSION/$DATES")
    suspend fun postTimeline(
        @Body requestTimelineDto: RequestTimelineDto
    )
}
