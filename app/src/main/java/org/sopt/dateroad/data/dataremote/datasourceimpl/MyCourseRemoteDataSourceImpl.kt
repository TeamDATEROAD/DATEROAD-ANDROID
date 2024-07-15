package org.sopt.dateroad.data.dataremote.datasourceimpl

import javax.inject.Inject
import org.sopt.dateroad.data.dataremote.datasource.MyCourseRemoteDataSource
import org.sopt.dateroad.data.dataremote.model.response.ResponseCoursesDto
import org.sopt.dateroad.data.dataremote.service.MyCourseService

class MyCourseRemoteDataSourceImpl @Inject constructor(
    private val myCourseService: MyCourseService
) : MyCourseRemoteDataSource {
    override suspend fun postMyCourseEnroll(): ResponseCoursesDto = myCourseService.postMyCourseEnroll()

    override suspend fun postMyCourseRead(): ResponseCoursesDto = myCourseService.postMyCourseRead()
}
