package org.sopt.teamdateroad.data.dataremote.service

import org.sopt.teamdateroad.data.dataremote.model.response.ResponseCoursesDto
import org.sopt.teamdateroad.data.dataremote.util.ApiConstraints.API
import org.sopt.teamdateroad.data.dataremote.util.ApiConstraints.COURSES
import org.sopt.teamdateroad.data.dataremote.util.ApiConstraints.DATE_ACCESS
import org.sopt.teamdateroad.data.dataremote.util.ApiConstraints.USERS
import org.sopt.teamdateroad.data.dataremote.util.ApiConstraints.VERSION
import retrofit2.http.GET

interface MyCourseService {
    @GET("$API/$VERSION/$COURSES/$USERS")
    suspend fun getMyCourseEnroll(): ResponseCoursesDto

    @GET("$API/$VERSION/$COURSES/$DATE_ACCESS")
    suspend fun getMyCourseRead(): ResponseCoursesDto
}
