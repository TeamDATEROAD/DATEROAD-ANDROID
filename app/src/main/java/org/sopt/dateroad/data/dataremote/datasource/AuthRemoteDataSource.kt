package org.sopt.dateroad.data.dataremote.datasource

import org.sopt.dateroad.data.dataremote.model.request.RequestWithdrawDto

interface AuthRemoteDataSource {
    suspend fun deleteWithdraw(requestWithdrawDto: RequestWithdrawDto)
}
