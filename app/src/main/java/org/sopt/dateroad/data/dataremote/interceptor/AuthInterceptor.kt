package org.sopt.dateroad.data.dataremote.interceptor

import javax.inject.Inject
import javax.inject.Provider
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import org.sopt.dateroad.data.dataremote.service.AuthService
import org.sopt.dateroad.domain.usecase.GetAccessTokenUseCase
import org.sopt.dateroad.domain.usecase.GetRefreshTokenUseCase
import org.sopt.dateroad.domain.usecase.RefreshTokenUseCase
import org.sopt.dateroad.domain.usecase.SaveAccessTokenUseCase

class AuthInterceptor @Inject constructor(
    private val getAccessTokenUseCase: GetAccessTokenUseCase,
    private val getRefreshTokenUseCase: GetRefreshTokenUseCase,
    private val refreshTokenUseCase: RefreshTokenUseCase,
    private val saveAccessTokenUseCase: SaveAccessTokenUseCase,
    private val authServiceProvider: Provider<AuthService>
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val authRequest = originalRequest.newAuthBuilder()

        var response = chain.proceed(authRequest)

        if (response.code == CODE_TOKEN_EXPIRE) {
            synchronized(this) {
                response.close()
                val refreshToken = runBlocking { getRefreshTokenUseCase() }
                val newAuth = runBlocking { refreshTokenUseCase(refreshToken) }
                runBlocking { saveAccessTokenUseCase(newAuth.accessToken) }

                val newRequest = originalRequest.newBuilder()
                    .removeHeader(AUTHORIZATION)
                    .addHeader(AUTHORIZATION, BEARER + newAuth.accessToken)
                    .build()

                response = chain.proceed(newRequest)
            }
        }

        return response
    }

    private fun Request.newAuthBuilder() = this.newBuilder()
        .addHeader(AUTHORIZATION, BEARER + runBlocking { getAccessTokenUseCase() })
        .build()

    companion object {
        const val CODE_TOKEN_EXPIRE = 401
        const val AUTHORIZATION = "Authorization"
        const val BEARER = "Bearer "
    }
}
