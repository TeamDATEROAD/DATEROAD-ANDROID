package org.sopt.dateroad.data.dataremote.service

import android.content.Context
import android.util.Log
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.user.UserApiClient
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject

class KakaoAuthService @Inject constructor(
    @ActivityContext private val context: Context,
    private val userApiClient: UserApiClient
) {
    private val isKakaoTalkLoginAvailable: Boolean
        get() = userApiClient.isKakaoTalkLoginAvailable(context)

    fun loginKakao(
        loginListener: ((String) -> Unit),
        accountListener: ((String) -> Unit)
    ) {
        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            if (error != null) {
                loginError(error)
            } else if (token != null) loginSuccess(token, loginListener, accountListener)
        }

        if (isKakaoTalkLoginAvailable) {
            userApiClient.loginWithKakaoTalk(context, callback = callback)
        } else {
            userApiClient.loginWithKakaoAccount(context, callback = callback)
        }
    }

    private fun loginError(throwable: Throwable) {
        val kakaoType = if (isKakaoTalkLoginAvailable) KAKAO_TALK else KAKAO_ACCOUNT
        Log.d("login", "{$kakaoType}으로 로그인 실패 ${throwable.message}")
    }

    private fun loginSuccess(
        oAuthToken: OAuthToken,
        loginListener: ((String) -> Unit),
        accountListener: ((String) -> Unit)
    ) {
        Log.d(KAKAO_ACCESS_TOKEN, oAuthToken.accessToken)
        userApiClient.me { user, error ->
            loginListener(oAuthToken.accessToken)
            if (error != null) {
                Log.e("login", "사용자 정보 요청 실패 $error")
            } else if (user != null) {
                accountListener(user.kakaoAccount?.profile?.nickname.orEmpty())
            }
        }
    }

    companion object {
        const val KAKAO_TALK = "카카오톡"
        const val KAKAO_ACCOUNT = "카카오계정"
        const val KAKAO_ACCESS_TOKEN = "카카오 엑세스 토큰"
    }
}
