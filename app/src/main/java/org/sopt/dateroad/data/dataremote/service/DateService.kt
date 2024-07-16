package org.sopt.dateroad.data.dataremote.service

import org.sopt.dateroad.data.dataremote.model.response.ResponseDateDetailDto
import org.sopt.dateroad.data.dataremote.util.ApiConstraints.API
import org.sopt.dateroad.data.dataremote.util.ApiConstraints.DATES
import org.sopt.dateroad.data.dataremote.util.ApiConstraints.VERSION
import org.sopt.dateroad.presentation.ui.timelinedetail.navigation.TimelineDetailRoutes.DATE_ID
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path

interface DateService {
    @DELETE("$API/$VERSION/$DATES/{$DATE_ID}")
    suspend fun deleteDate(
        @Path(DATE_ID) dateId: Long
    )

    @GET("$API/$VERSION/$DATES/{$DATE_ID}")
    suspend fun getDateDetail(
        @Path(DATE_ID) dateId: Long
    ): ResponseDateDetailDto
}
