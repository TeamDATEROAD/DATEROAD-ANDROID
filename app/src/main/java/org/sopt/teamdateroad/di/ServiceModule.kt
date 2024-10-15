package org.sopt.teamdateroad.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.teamdateroad.data.dataremote.service.AdvertisementService
import org.sopt.teamdateroad.data.dataremote.service.AuthService
import org.sopt.teamdateroad.data.dataremote.service.CourseService
import org.sopt.teamdateroad.data.dataremote.service.MyCourseService
import org.sopt.teamdateroad.data.dataremote.service.ProfileService
import org.sopt.teamdateroad.data.dataremote.service.TimelineService
import org.sopt.teamdateroad.data.dataremote.service.UserPointService
import org.sopt.teamdateroad.di.qualifier.DateRoad
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {
    @Provides
    fun providesAdvertisementService(@DateRoad retrofit: Retrofit): AdvertisementService =
        retrofit.create(AdvertisementService::class.java)

    @Provides
    fun providesAuthService(@DateRoad retrofit: Retrofit): AuthService =
        retrofit.create(AuthService::class.java)

    @Provides
    fun providesCourseService(@DateRoad retrofit: Retrofit): CourseService =
        retrofit.create(CourseService::class.java)

    @Provides
    fun providesMyCourseService(@DateRoad retrofit: Retrofit): MyCourseService =
        retrofit.create(MyCourseService::class.java)

    @Provides
    fun providesProfileService(@DateRoad retrofit: Retrofit): ProfileService =
        retrofit.create(ProfileService::class.java)

    @Provides
    fun provideTimelineService(@DateRoad retrofit: Retrofit): TimelineService =
        retrofit.create(TimelineService::class.java)

    @Provides
    fun providesUserPointService(@DateRoad retrofit: Retrofit): UserPointService =
        retrofit.create(UserPointService::class.java)
}
