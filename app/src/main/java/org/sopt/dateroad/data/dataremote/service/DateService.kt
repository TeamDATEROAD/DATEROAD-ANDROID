package org.sopt.dateroad.data.dataremote.service

import org.sopt.dateroad.data.dataremote.model.request.RequestDateDto
import org.sopt.dateroad.data.dataremote.model.response.ResponseDateDetailDto
import org.sopt.dateroad.data.dataremote.model.response.ResponseDatesDto
import org.sopt.dateroad.data.dataremote.model.response.ResponseNearestDateDto
import org.sopt.dateroad.data.dataremote.util.ApiConstraints.API
import org.sopt.dateroad.data.dataremote.util.ApiConstraints.DATES
import org.sopt.dateroad.data.dataremote.util.ApiConstraints.NEAREST
import org.sopt.dateroad.data.dataremote.util.ApiConstraints.TIME
import org.sopt.dateroad.data.dataremote.util.ApiConstraints.VERSION
import org.sopt.dateroad.presentation.ui.timelinedetail.navigation.TimelineDetailRoutes.DATE_ID
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface DateService {
    @DELETE("$API/$VERSION/$DATES/{$DATE_ID}")
    suspend fun deleteDate(
        @Path(DATE_ID) dateId: Int
    )

    @GET("$API/$VERSION/$DATES/{$DATE_ID}")
    suspend fun getDateDetail(
        @Path(DATE_ID) dateId: Int
    ): ResponseDateDetailDto

    @GET("$API/$VERSION/$DATES")
    suspend fun getDates(
        @Query(TIME) time: String
    ): ResponseDatesDto

    @GET("$API/$VERSION/$DATES/$NEAREST")
    suspend fun getNearestDate(): ResponseNearestDateDto

    @POST("$API/$VERSION/$DATES")
    suspend fun postDate(
        @Body requestDateDto: RequestDateDto
    )
}
