package org.sopt.dateroad.data.dataremote.interceptor

import javax.inject.Inject
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val authRequest = originalRequest.newBuilder().addHeader(AUTHORIZATION, "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI2IiwiaWF0IjoxNzIxMjA2MTc5LCJleHAiOjE3MjM2MjUyNzl9.ZVq3q2jNdcTXIcvHtg1EbcNpNVqOlFeElEIRRwVI3CU").build()
        val response = chain.proceed(authRequest)

        when (response.code) {
            CODE_TOKEN_EXPIRE -> {
                // TODO 토큰 재발급 api 연동
            }
        }
        return response
    }

    companion object {
        const val CODE_TOKEN_EXPIRE = 401
        const val AUTHORIZATION = "Authorization"
        const val BEARER = "Bearer "
    }
}
