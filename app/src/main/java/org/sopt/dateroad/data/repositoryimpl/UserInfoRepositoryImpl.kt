package org.sopt.dateroad.data.repositoryimpl

import javax.inject.Inject
import org.sopt.dateroad.data.datalocal.datasource.UserInfoLocalDataSource
import org.sopt.dateroad.domain.repository.UserInfoRepository

class UserInfoRepositoryImpl @Inject constructor(
    private val userInfoLocalDataSource: UserInfoLocalDataSource
) : UserInfoRepository {
    override fun setIsLogin(isLogin: Boolean) {
        userInfoLocalDataSource.isLogin = isLogin
    }
    override fun getIsLogin(): Boolean = userInfoLocalDataSource.isLogin

    override fun setAccessToken(accessToken: String) {
        userInfoLocalDataSource.accessToken = accessToken
    }
    override fun getAccessToken(): String = userInfoLocalDataSource.accessToken

    override fun setRefreshToken(refreshToken: String) {
        userInfoLocalDataSource.refreshToken = refreshToken
    }
    override fun getRefreshToken(): String = userInfoLocalDataSource.refreshToken

    override fun clearUserInfo() = userInfoLocalDataSource.clear()
}
