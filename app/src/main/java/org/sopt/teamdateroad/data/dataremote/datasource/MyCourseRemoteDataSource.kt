package org.sopt.teamdateroad.data.dataremote.datasource

import org.sopt.teamdateroad.data.dataremote.model.response.ResponseCoursesDto

interface MyCourseRemoteDataSource {
    suspend fun getMyCourseEnroll(): ResponseCoursesDto

    suspend fun getMyCourseRead(): ResponseCoursesDto
}
