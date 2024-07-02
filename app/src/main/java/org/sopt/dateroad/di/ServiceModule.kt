package org.sopt.dateroad.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.dateroad.data.dataremote.service.DummyService
import org.sopt.dateroad.di.qualifier.DateRoad
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {
    @Provides
    fun providesDummyService(@DateRoad retrofit: Retrofit): DummyService =
        retrofit.create(DummyService::class.java)
}
