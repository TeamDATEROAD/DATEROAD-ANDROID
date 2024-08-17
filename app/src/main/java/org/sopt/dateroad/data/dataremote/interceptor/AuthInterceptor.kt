package org.sopt.dateroad.data.dataremote.interceptor

import android.app.Application
import android.content.Intent
import java.lang.IllegalStateException
import java.util.concurrent.atomic.AtomicBoolean
import javax.inject.Inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import org.sopt.dateroad.BuildConfig
import org.sopt.dateroad.data.datalocal.datasource.UserInfoLocalDataSource
import org.sopt.dateroad.data.dataremote.model.response.ResponseRefreshTokenDto
import org.sopt.dateroad.data.dataremote.util.ApiConstraints.API
import org.sopt.dateroad.data.dataremote.util.ApiConstraints.REISSUE
import org.sopt.dateroad.data.dataremote.util.ApiConstraints.USERS
import org.sopt.dateroad.data.dataremote.util.ApiConstraints.VERSION

class AuthInterceptor @Inject constructor(
    private val json: Json,
    private val localStorage: UserInfoLocalDataSource,
    private val context: Application
) : Interceptor {
    private val isRefreshing = AtomicBoolean(false)

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val authRequest = if (localStorage.accessToken.isNotBlank()) originalRequest.newAuthBuilder() else originalRequest
        val response = chain.proceed(authRequest)

        when (response.code) {
            CODE_TOKEN_EXPIRE -> {
                response.close()

                if (isRefreshing.compareAndSet(false, true)) {
                    try {
                        val refreshTokenRequest = originalRequest.newBuilder()
                            .patch("".toRequestBody(null))
                            .url("${BuildConfig.BASE_URL}$API/$VERSION/$USERS/$REISSUE")
                            .addHeader(AUTHORIZATION, localStorage.refreshToken)
                            .build()

                        val refreshTokenResponse = chain.proceed(refreshTokenRequest)

                        if (refreshTokenResponse.isSuccessful) {
                            val responseRefresh = json.decodeFromString<ResponseRefreshTokenDto>(
                                refreshTokenResponse.body?.string()
                                    ?: throw IllegalStateException("\"refreshTokenResponse is null $refreshTokenResponse\"")
                            )

                            with(localStorage) {
                                accessToken = BEARER + responseRefresh.accessToken
                                refreshToken = responseRefresh.refreshToken
                            }
                            refreshTokenResponse.close()

                            val newRequest = originalRequest.newAuthBuilder()
                            return chain.proceed(newRequest)
                        } else {
                            with(context) {
                                CoroutineScope(Dispatchers.Main).launch {
                                    startActivity(
                                        Intent.makeRestartActivityTask(
                                            packageManager.getLaunchIntentForPackage(packageName)?.component
                                        )
                                    )
                                    localStorage.clear()
                                }
                            }
                        }
                    } finally {
                        isRefreshing.set(false)
                    }
                }
            }
        }

        return response
    }

    private fun Request.newAuthBuilder() =
        this.newBuilder().addHeader(AUTHORIZATION, localStorage.accessToken).build()

    companion object {
        const val CODE_TOKEN_EXPIRE = 401
        const val AUTHORIZATION = "Authorization"
        const val BEARER = "Bearer "
    }
}
