package org.sopt.dateroad

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.kakao.sdk.common.KakaoSdk
import dagger.hilt.android.HiltAndroidApp
import org.sopt.dateroad.BuildConfig.KAKAO_NATIVE_APP_KEY
import org.sopt.dateroad.presentation.util.amplitude.AmplitudeUtils.initAmplitude
import timber.log.Timber

@HiltAndroidApp
class DateRoadApp : Application() {
    override fun onCreate() {
        super.onCreate()

        setTimber()
        setDarkMode()
        setKakao()
        initAmplitude(applicationContext)
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
