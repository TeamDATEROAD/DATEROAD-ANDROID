package org.sopt.dateroad.data.datalocal.datasource

interface UserInfoLocalDataSource {
    var isLogin: Boolean
    var accessToken : String
    fun clear()
}
