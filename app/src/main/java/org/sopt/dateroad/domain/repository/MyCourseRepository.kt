package org.sopt.dateroad.domain.repository

import org.sopt.dateroad.domain.model.Course

interface MyCourseRepository {
    suspend fun postMyCourseEnroll(): Result<List<Course>>

    suspend fun postMyCourseRead(): Result<List<Course>>
}
