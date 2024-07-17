package org.sopt.dateroad.domain.repository

interface UserInfoRepository {
    fun setIsLogin(isLogin: Boolean)
    fun getIsLogin(): Boolean
    fun clearUserInfo()
    fun setUserId(userId: String)
    fun getUserId(): String
    fun setRemainingPoints(remainingPoints: Int)
    fun getRemainingPoints(): Int
}
