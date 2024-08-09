package org.sopt.dateroad.data.dataremote.interceptor

import android.app.Application
import android.content.Intent
import javax.inject.Inject
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
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
    private val mutex = Mutex()
    private var isRefreshing = false
    private var newAccessToken: String? = null

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val authRequest = originalRequest.newAuthBuilder()

        var response = chain.proceed(authRequest)
        if (response.code == CODE_TOKEN_EXPIRE) {
            val refreshTokenDeferred = CompletableDeferred<Unit>()
            runBlocking {
                mutex.withLock {
                    if (!isRefreshing) {
                        isRefreshing = true
                        try {
                            val refreshToken = localStorage.refreshToken
                            if (refreshToken.isBlank()) {
                                throw IllegalStateException("\"refreshTokenResponse is null $refreshToken\"")
                            }

                            val refreshTokenRequest = Request.Builder()
                                .url("${BuildConfig.BASE_URL}$API/$VERSION/$USERS/$REISSUE")
                                .patch("".toRequestBody(null))
                                .addHeader(CONTENT_TYPE, APPLICATION_JSON)
                                .addHeader(AUTHORIZATION, refreshToken)
                                .build()

                            val refreshTokenResponse = chain.proceed(refreshTokenRequest)
                            if (refreshTokenResponse.isSuccessful) {
                                val responseBody = refreshTokenResponse.body?.string()
                                if (responseBody != null) {
                                    val responseRefresh = json.decodeFromString<ResponseRefreshTokenDto>(responseBody)

                                    localStorage.accessToken = responseRefresh.accessToken
                                    localStorage.refreshToken = responseRefresh.refreshToken
                                    newAccessToken = responseRefresh.accessToken
                                }
                            }
                        } finally {
                            isRefreshing = false
                        }
                    } else {
                        refreshTokenDeferred.await()
                    }
                }
            }

            if (newAccessToken != null) {
                val newRequest = originalRequest.newAuthBuilder()
                return chain.proceed(newRequest)
            }

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

        return response
    }

    private fun Request.newAuthBuilder() =
        this.newBuilder()
            .addHeader(AUTHORIZATION, "$BEARER${localStorage.accessToken}")
            .build()

    companion object {
        const val CODE_TOKEN_EXPIRE = 401
        const val CONTENT_TYPE = "Content-Type"
        const val APPLICATION_JSON = "application/json"
        const val AUTHORIZATION = "Authorization"
        const val BEARER = "Bearer "
    }
}
