package org.sopt.teamdateroad.data.dataremote.service

import org.sopt.teamdateroad.data.dataremote.model.request.RequestUsePointDto
import org.sopt.teamdateroad.data.dataremote.model.response.ResponsePointHistoryDto
import org.sopt.teamdateroad.data.dataremote.model.response.ResponseUserPointDto
import org.sopt.teamdateroad.data.dataremote.model.response.ResponseUserUsePointDto
import org.sopt.teamdateroad.data.dataremote.util.ApiConstraints.API
import org.sopt.teamdateroad.data.dataremote.util.ApiConstraints.COURSES
import org.sopt.teamdateroad.data.dataremote.util.ApiConstraints.COURSE_ID
import org.sopt.teamdateroad.data.dataremote.util.ApiConstraints.DATE_ACCESS
import org.sopt.teamdateroad.data.dataremote.util.ApiConstraints.MAIN
import org.sopt.teamdateroad.data.dataremote.util.ApiConstraints.POINTS
import org.sopt.teamdateroad.data.dataremote.util.ApiConstraints.USERS
import org.sopt.teamdateroad.data.dataremote.util.ApiConstraints.VERSION
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UserPointService {
    @GET("$API/$VERSION/$USERS/$MAIN")
    suspend fun getUserPoint(): ResponseUserPointDto

    @GET("$API/$VERSION/$POINTS")
    suspend fun getPointHistory(): ResponsePointHistoryDto

    @POST("$API/$VERSION/$COURSES/{$COURSE_ID}/$DATE_ACCESS")
    suspend fun postUsePoint(
        @Path(COURSE_ID) courseId: Int,
        @Body requestUsePointDto: RequestUsePointDto
    ): ResponseUserUsePointDto
}
