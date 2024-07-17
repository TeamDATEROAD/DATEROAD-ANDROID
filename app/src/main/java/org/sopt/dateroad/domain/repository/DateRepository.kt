package org.sopt.dateroad.domain.repository

import org.sopt.dateroad.domain.model.Enroll

interface DateRepository {
    suspend fun postDate(date: Enroll): Result<Unit>
}
