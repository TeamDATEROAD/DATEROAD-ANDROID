package org.sopt.dateroad.data.datalocal.datasource

interface UserInfoLocalDataSource {
    var isLogin: Boolean
    var accessToken : String
    var refreshToken : String
    fun clear()
}
