package org.sopt.dateroad.data.dataremote.datasource

import org.sopt.dateroad.data.dataremote.model.request.RequestDummyDto

interface AuthRemoteDataSource {
    suspend fun getNicknameCheck(name: String)
    suspend fun postSignIn(requestDummyDto: RequestDummyDto)
    suspend fun postSignUp(requestDummyDto: RequestDummyDto)
    suspend fun deleteWithdraw(userId: Int, authCode: String)
    suspend fun deleteSignOut(userId: Int)
}
