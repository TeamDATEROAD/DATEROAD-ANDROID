package org.sopt.dateroad.domain.repository

interface UserInfoRepository {
    fun setIsLogin(isLogin: Boolean)
    fun getIsLogin(): Boolean
    fun setAccessToken(accessToken: String)
    fun getAccessToken(): String
    fun setRefreshToken(refreshToken: String)
    fun getRefreshToken(): String
    fun clearUserInfo()
}
