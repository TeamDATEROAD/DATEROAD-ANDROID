package org.sopt.dateroad.data.datalocal.datasourceimpl

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.kakao.sdk.auth.Constants.ACCESS_TOKEN
import com.kakao.sdk.auth.Constants.REFRESH_TOKEN
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import org.sopt.dateroad.BuildConfig
import org.sopt.dateroad.data.datalocal.datasource.UserInfoLocalDataSource

class UserInfoLocalDataSourceImpl @Inject constructor(
    @ApplicationContext context: Context
) : UserInfoLocalDataSource {
    private val masterKey = MasterKey.Builder(context, MasterKey.DEFAULT_MASTER_KEY_ALIAS)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()

    private val sharedPreferences: SharedPreferences = if (BuildConfig.DEBUG) {
        context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
    } else {
        EncryptedSharedPreferences.create(
            context,
            FILE_NAME,
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    override var accessToken: String
        get() = sharedPreferences.getString(ACCESS_TOKEN, "").toString()
        set(value) = sharedPreferences.edit { putString(ACCESS_TOKEN, value) }

    override var refreshToken: String
        get() = sharedPreferences.getString(REFRESH_TOKEN, "").toString()
        set(value) = sharedPreferences.edit { putString(REFRESH_TOKEN, value) }

    override var nickname: String
        get() = sharedPreferences.getString(NICK_NAME, "").toString()
        set(value) = sharedPreferences.edit { putString(NICK_NAME, value) }

    override fun clear() = sharedPreferences.edit { clear() }

    companion object {
        const val FILE_NAME = "DateRoadLocalDataSource"
        const val NICK_NAME = "NickName"
    }
}
