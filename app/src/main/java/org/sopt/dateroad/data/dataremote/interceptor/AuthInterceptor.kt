package org.sopt.dateroad.data.dataremote.interceptor

import javax.inject.Inject
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val authRequest = originalRequest.newBuilder().addHeader(AUTHORIZATION, "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI1IiwiaWF0IjoxNzIxMTE5ODgxLCJleHAiOjE3MjM1Mzg5ODF9.0kNA6ZHP2C433VaMjn-hMdux7xGm_-2DWtkzr6PBTIE").build()
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
