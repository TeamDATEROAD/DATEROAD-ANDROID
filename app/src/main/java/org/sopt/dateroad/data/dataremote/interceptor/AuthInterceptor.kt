package org.sopt.dateroad.data.dataremote.interceptor

import javax.inject.Inject
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import org.sopt.dateroad.domain.usecase.GetAccessTokenUseCase

class AuthInterceptor @Inject constructor(
    private val getAccessTokenUseCase: GetAccessTokenUseCase
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val authRequest = originalRequest.newAuthBuilder()
        val response = chain.proceed(authRequest)

        when (response.code) {
            CODE_TOKEN_EXPIRE -> {
                // TODO 토큰 재발급 api 연동
            }
        }
        return response
    }

    private fun Request.newAuthBuilder() = this.newBuilder().addHeader(AUTHORIZATION, getAccessTokenUseCase()).build()

    companion object {
        const val CODE_TOKEN_EXPIRE = 401
        const val AUTHORIZATION = "Authorization"
        const val BEARER = "Bearer "
    }
}
