package org.sopt.dateroad.data.datalocal.datasourceimpl

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import dagger.hilt.android.qualifiers.ApplicationContext
import org.sopt.dateroad.BuildConfig
import org.sopt.dateroad.data.datalocal.datasource.UserInfoLocalDataSource
import javax.inject.Inject

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

    override var isLogin: Boolean
        get() = sharedPreferences.getBoolean(IS_LOGIN, false)
        set(value) = sharedPreferences.edit { putBoolean(IS_LOGIN, value) }

    override fun clear() = sharedPreferences.edit { clear() }

    companion object {
        const val FILE_NAME = "DateRoadLocalDataSource"
        const val IS_LOGIN = "IsLogin"
    }
}