package org.sopt.dateroad.data.dataremote.datasource

import org.sopt.dateroad.data.dataremote.model.response.ResponseCoursesDto

interface MyCourseRemoteDataSource {
    suspend fun postMyCourseEnroll(): ResponseCoursesDto

    suspend fun postMyCourseRead(): ResponseCoursesDto
}
