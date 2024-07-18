package org.sopt.dateroad.data.datalocal.datasource

interface UserInfoLocalDataSource {
    var isLogin: Boolean
    var nickname: String
    var remainingPoints: Int
    fun clear()
}
