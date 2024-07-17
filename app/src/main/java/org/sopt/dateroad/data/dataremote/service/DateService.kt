package org.sopt.dateroad.data.dataremote.service

import org.sopt.dateroad.data.dataremote.model.request.RequestDateDto
import org.sopt.dateroad.data.dataremote.util.ApiConstraints.API
import org.sopt.dateroad.data.dataremote.util.ApiConstraints.DATES
import org.sopt.dateroad.data.dataremote.util.ApiConstraints.VERSION
import retrofit2.http.Body
import retrofit2.http.POST

interface DateService {
    @POST("$API/$VERSION/$DATES")
    suspend fun postDate(
        @Body requestDateDto: RequestDateDto
    )
}
