package org.sopt.dateroad.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.dateroad.data.dataremote.service.AdvertisementService
import org.sopt.dateroad.data.dataremote.service.DateService
import org.sopt.dateroad.data.dataremote.service.DummyService
import org.sopt.dateroad.data.dataremote.service.MyCourseService
import org.sopt.dateroad.di.qualifier.DateRoad
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {
    @Provides
    fun providesDummyService(@DateRoad retrofit: Retrofit): DummyService =
        retrofit.create(DummyService::class.java)

    @Provides
    fun providesMyCourseService(@DateRoad retrofit: Retrofit): MyCourseService =
        retrofit.create(MyCourseService::class.java)

    @Provides
    fun providesAdvertisementService(@DateRoad retrofit: Retrofit): AdvertisementService =
        retrofit.create(AdvertisementService::class.java)

    @Provides
    fun provideDateService(@DateRoad retrofit: Retrofit): DateService =
        retrofit.create(DateService::class.java)
}
