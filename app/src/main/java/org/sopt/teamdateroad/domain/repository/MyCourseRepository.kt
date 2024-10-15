package org.sopt.teamdateroad.domain.repository

import org.sopt.teamdateroad.domain.model.Course

interface MyCourseRepository {
    suspend fun getMyCourseEnroll(): Result<List<Course>>

    suspend fun getMyCourseRead(): Result<List<Course>>
}
