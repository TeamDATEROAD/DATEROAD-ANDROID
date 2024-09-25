package org.sopt.dateroad.data.dataremote.datasource

import org.sopt.dateroad.data.dataremote.model.response.ResponseCoursesDto

interface MyCourseRemoteDataSource {
    suspend fun getMyCourseEnroll(): ResponseCoursesDto

    suspend fun getMyCourseRead(): ResponseCoursesDto
}
