package org.sopt.dateroad.domain.repository

import org.sopt.dateroad.domain.model.Course

interface MyCourseRepository {
    suspend fun getMyCourseEnroll(): Result<List<Course>>

    suspend fun getMyCourseRead(): Result<List<Course>>
}
