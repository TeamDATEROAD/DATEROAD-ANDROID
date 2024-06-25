package org.sopt.dateroad.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.sopt.dateroad.BuildConfig
import org.sopt.dateroad.BuildConfig.DEBUG
import org.sopt.dateroad.data.dataremote.interceptor.AuthInterceptor
import org.sopt.dateroad.di.qualifier.Auth
import org.sopt.dateroad.di.qualifier.DateRoad
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @OptIn(ExperimentalSerializationApi::class)
    @Provides
    @Singleton
    fun providesJson(): Json =
        Json {
            isLenient = true
            prettyPrint = true
            explicitNulls = false
            ignoreUnknownKeys = true
        }

    @Provides
    @Singleton
    fun providesOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        @Auth authInterceptor: Interceptor,
    ): OkHttpClient =
        OkHttpClient.Builder().apply {
            connectTimeout(10, TimeUnit.SECONDS)
            writeTimeout(10, TimeUnit.SECONDS)
            readTimeout(10, TimeUnit.SECONDS)
            addInterceptor(authInterceptor)
            if (DEBUG) addInterceptor(loggingInterceptor)
        }.build()

    @Provides
    @Singleton
    fun providesLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Provides
    @Singleton
    @Auth
    fun provideAuthInterceptor(interceptor: AuthInterceptor): Interceptor = interceptor

    @ExperimentalSerializationApi
    @Provides
    @DateRoad
    @Singleton
    fun providesPingleRetrofit(
        okHttpClient: OkHttpClient,
        json: Json,
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(
                json.asConverterFactory(requireNotNull("application/json".toMediaTypeOrNull())),
            )
            .build()
}