package org.sopt.dateroad.data.dataremote.datasourceimpl

import javax.inject.Inject
import org.sopt.dateroad.data.dataremote.datasource.MyCourseRemoteDataSource
import org.sopt.dateroad.data.dataremote.model.response.ResponseCoursesDto
import org.sopt.dateroad.data.dataremote.service.MyCourseService

class MyCourseRemoteDataSourceImpl @Inject constructor(
    private val myCourseService: MyCourseService
) : MyCourseRemoteDataSource {
    override suspend fun getMyCourseEnroll(): ResponseCoursesDto = myCourseService.getMyCourseEnroll()

    override suspend fun getMyCourseRead(): ResponseCoursesDto = myCourseService.getMyCourseRead()
}
