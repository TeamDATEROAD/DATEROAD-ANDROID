package org.sopt.dateroad.data.dataremote.datasource

import org.sopt.dateroad.data.dataremote.model.base.BaseResponse
import org.sopt.dateroad.data.dataremote.model.request.RequestDummyDto

interface AuthRemoteDataSource {
    suspend fun getNicknameCheck(name: String)
    suspend fun postSignIn(requestDummyDto: RequestDummyDto): BaseResponse<Unit>
    suspend fun postSignUp(requestDummyDto: RequestDummyDto): BaseResponse<Unit>
    suspend fun deleteWithdraw(userId: Int, authCode: String): BaseResponse<Unit>
    suspend fun deleteSignOut(userId: Int): BaseResponse<Unit>
}
