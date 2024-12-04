package org.sopt.teamdateroad.domain.repository

interface UserInfoRepository {
    fun setAccessToken(accessToken: String)

    fun getAccessToken(): String

    fun setRefreshToken(refreshToken: String)

    fun getRefreshToken(): String

    fun setNickname(nickname: String)

    fun getNickname(): String

    fun clear()
}
