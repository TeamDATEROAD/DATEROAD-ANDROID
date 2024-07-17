package org.sopt.dateroad.data.dataremote.service

import org.sopt.dateroad.data.dataremote.model.request.RequestUsePointDto
import org.sopt.dateroad.data.dataremote.model.response.ResponsePointHistoryDto
import org.sopt.dateroad.data.dataremote.model.response.ResponseUserPointDto
import org.sopt.dateroad.data.dataremote.util.ApiConstraints.API
import org.sopt.dateroad.data.dataremote.util.ApiConstraints.COURSES
import org.sopt.dateroad.data.dataremote.util.ApiConstraints.COURSE_ID
import org.sopt.dateroad.data.dataremote.util.ApiConstraints.DATE_ACCESS
import org.sopt.dateroad.data.dataremote.util.ApiConstraints.MAIN
import org.sopt.dateroad.data.dataremote.util.ApiConstraints.POINTS
import org.sopt.dateroad.data.dataremote.util.ApiConstraints.USERS
import org.sopt.dateroad.data.dataremote.util.ApiConstraints.USER_ID
import org.sopt.dateroad.data.dataremote.util.ApiConstraints.VERSION
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface UserPointService {
    @GET("$API/$VERSION/$USERS/$MAIN")
    suspend fun getUserPoint(
        @Query(USER_ID) userId: Int
    ): ResponseUserPointDto

    @GET("$API/$VERSION/$POINTS")
    suspend fun getPointHistory(): ResponsePointHistoryDto

    @POST("$API/$VERSION/$COURSES/{$COURSE_ID}/$DATE_ACCESS")
    suspend fun postUsePoint(
        @Path(COURSE_ID) courseId: Int,
        @Body requestUsePointDto: RequestUsePointDto
    )
}
