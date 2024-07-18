package org.sopt.dateroad.data.datalocal.datasource

interface UserInfoLocalDataSource {
    var accessToken: String
    var refreshToken: String
    var nickname: String
    fun clear()
}
