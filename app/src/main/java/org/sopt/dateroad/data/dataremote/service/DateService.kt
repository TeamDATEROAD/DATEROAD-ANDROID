package org.sopt.dateroad.data.dataremote.service

import org.sopt.dateroad.data.dataremote.util.ApiConstraints.API
import org.sopt.dateroad.data.dataremote.util.ApiConstraints.DATES
import org.sopt.dateroad.data.dataremote.util.ApiConstraints.VERSION
import retrofit2.http.DELETE
import retrofit2.http.Path

interface DateService {
    @DELETE("$API/$VERSION/$DATES/{dateId}")
    suspend fun deleteDate(
        @Path("dateId") dateId: Long
    )
}
