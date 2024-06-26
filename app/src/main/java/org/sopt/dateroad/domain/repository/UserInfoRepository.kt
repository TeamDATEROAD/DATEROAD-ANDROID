package org.sopt.dateroad.domain.repository

interface UserInfoRepository {
    fun setIsLogin(isLogin: Boolean)
    fun getIsLogin(): Boolean
    fun clearUserInfo()
}