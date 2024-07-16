package org.sopt.dateroad.domain.repository

import org.sopt.dateroad.domain.model.Profile

interface ProfileRepository {
    suspend fun getUsers(userId: Int): Result<Profile>
}
