package org.sopt.dateroad.data.dataremote.interceptor

import android.app.Application
import android.content.Intent
import javax.inject.Inject
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

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val authRequest = if (localStorage.accessToken.isNotBlank()) originalRequest.newAuthBuilder() else originalRequest
        var response = chain.proceed(authRequest)

        when (response.code) {
            CODE_TOKEN_EXPIRE -> {
                response.close()
                response = handleTokenExpiration(chain = chain, originalRequest = originalRequest, requestAccessToken = localStorage.accessToken)
            }
        }

        return response
    }

    private fun Request.newAuthBuilder() =
        this.newBuilder().addHeader(AUTHORIZATION, localStorage.accessToken).build()

    private fun handleTokenExpiration(chain: Interceptor.Chain, originalRequest: Request, requestAccessToken: String): Response =
        runBlocking {
            mutex.withLock {
                when (isTokenValid(requestAccessToken = requestAccessToken, currentAccessToken = localStorage.accessToken)) {
                    true -> chain.proceed(originalRequest.newAuthBuilder())
                    false -> handleTokenRefresh(chain = chain, originalRequest = originalRequest, refreshToken = localStorage.refreshToken)
                }
            }
        }

    private fun isTokenValid(requestAccessToken: String, currentAccessToken: String): Boolean = requestAccessToken != currentAccessToken && currentAccessToken.isNotBlank()

    private fun handleTokenRefresh(chain: Interceptor.Chain, originalRequest: Request, refreshToken: String): Response =
        patchTokenRefresh(chain = chain, originalRequest = originalRequest, refreshToken = refreshToken).let { refreshTokenResponse ->
            when (refreshTokenResponse.isSuccessful) {
                true -> handleTokenRefreshSuccess(chain = chain, originalRequest = originalRequest, refreshTokenResponse = refreshTokenResponse)
                false -> handleTokenRefreshFailed(refreshTokenResponse = refreshTokenResponse)
            }
        }

    private fun patchTokenRefresh(chain: Interceptor.Chain, originalRequest: Request, refreshToken: String): Response = chain.proceed(
        originalRequest.newBuilder()
            .patch("".toRequestBody(null))
            .url("${BuildConfig.BASE_URL}$API/$VERSION/$USERS/$REISSUE")
            .addHeader(AUTHORIZATION, refreshToken)
            .build()
    )

    private fun handleTokenRefreshSuccess(chain: Interceptor.Chain, originalRequest: Request, refreshTokenResponse: Response): Response {
        val responseRefreshToken = json.decodeFromString<ResponseRefreshTokenDto>(
            refreshTokenResponse.body?.string() ?: throw IllegalStateException("\"refreshTokenResponse is null $refreshTokenResponse\"")
        )

        with(localStorage) {
            accessToken = BEARER + responseRefreshToken.accessToken
            refreshToken = responseRefreshToken.refreshToken
        }

        refreshTokenResponse.close()

        return chain.proceed(originalRequest.newAuthBuilder())
    }

    private fun handleTokenRefreshFailed(refreshTokenResponse: Response): Response {
        refreshTokenResponse.close()

        CoroutineScope(Dispatchers.Main).launch {
            with(context) {
                CoroutineScope(Dispatchers.Main).launch {
                    startActivity(
                        Intent.makeRestartActivityTask(
                            packageManager.getLaunchIntentForPackage(packageName)?.component
                        )
                    )
                }
            }
        }

        localStorage.clear()

        return refreshTokenResponse
    }

    companion object {
        const val CODE_TOKEN_EXPIRE = 401
        const val AUTHORIZATION = "Authorization"
        const val BEARER = "Bearer "
    }
}
