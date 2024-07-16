package org.sopt.dateroad.domain.repository

interface AuthRepository {
    suspend fun getNicknameCheck(name: String)
}
