package org.sopt.teamdateroad.domain.repository

import org.sopt.teamdateroad.domain.model.Profile

interface ProfileRepository {
    suspend fun getUsers(): Result<Profile>
}
