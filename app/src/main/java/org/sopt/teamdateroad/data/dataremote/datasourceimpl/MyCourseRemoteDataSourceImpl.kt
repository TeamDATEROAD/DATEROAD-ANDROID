package org.sopt.teamdateroad.data.dataremote.datasourceimpl

import javax.inject.Inject
import org.sopt.teamdateroad.data.dataremote.datasource.MyCourseRemoteDataSource
import org.sopt.teamdateroad.data.dataremote.model.response.ResponseCoursesDto
import org.sopt.teamdateroad.data.dataremote.service.MyCourseService

class MyCourseRemoteDataSourceImpl @Inject constructor(
    private val myCourseService: MyCourseService
) : MyCourseRemoteDataSource {
    override suspend fun getMyCourseEnroll(): ResponseCoursesDto = myCourseService.getMyCourseEnroll()

    override suspend fun getMyCourseRead(): ResponseCoursesDto = myCourseService.getMyCourseRead()
}
