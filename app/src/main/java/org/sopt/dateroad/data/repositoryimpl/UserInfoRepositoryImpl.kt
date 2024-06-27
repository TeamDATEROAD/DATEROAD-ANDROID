package org.sopt.dateroad.data.repositoryimpl

import org.sopt.dateroad.data.datalocal.datasource.UserInfoLocalDataSource
import org.sopt.dateroad.domain.repository.UserInfoRepository
import javax.inject.Inject

class UserInfoRepositoryImpl @Inject constructor(
    private val userInfoLocalDataSource: UserInfoLocalDataSource
) : UserInfoRepository {
    override fun setIsLogin(isLogin: Boolean) {
        userInfoLocalDataSource.isLogin = isLogin
    }

    override fun getIsLogin(): Boolean = userInfoLocalDataSource.isLogin

    override fun clearUserInfo() = userInfoLocalDataSource.clear()
}
