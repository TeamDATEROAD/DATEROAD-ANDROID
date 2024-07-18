package org.sopt.dateroad

import android.app.Application
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import com.kakao.sdk.common.KakaoSdk
import com.kakao.sdk.common.util.Utility
import dagger.hilt.android.HiltAndroidApp
import org.sopt.dateroad.BuildConfig.KAKAO_NATIVE_APP_KEY
import timber.log.Timber

@HiltAndroidApp
class DateRoadApp : Application() {
    override fun onCreate() {
        super.onCreate()
        var keyHash = Utility.getKeyHash(this)
        Log.i("GlobalApplication", "$keyHash")
        setTimber()
        setDarkMode()
        setKakao()
    }

    private fun setTimber() {
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }

    private fun setDarkMode() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }

    private fun setKakao() {
        KakaoSdk.init(this, KAKAO_NATIVE_APP_KEY)
    }
}
