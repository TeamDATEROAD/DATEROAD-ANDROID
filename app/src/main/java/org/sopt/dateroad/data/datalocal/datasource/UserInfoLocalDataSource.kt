package org.sopt.dateroad.data.datalocal.datasource

interface UserInfoLocalDataSource {
    var isLogin: Boolean
    var userId: String
    var remainingPoints: Int
    fun clear()
}
