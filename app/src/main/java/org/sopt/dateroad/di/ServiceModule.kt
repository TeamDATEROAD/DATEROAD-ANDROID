package org.sopt.dateroad.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.dateroad.data.datalocal.datasource.UserInfoLocalDataSource
import org.sopt.dateroad.data.dataremote.service.AdvertisementService
import org.sopt.dateroad.data.dataremote.service.AuthService
import org.sopt.dateroad.data.dataremote.service.CourseService
import org.sopt.dateroad.data.dataremote.service.DummyService
import org.sopt.dateroad.data.dataremote.service.MyCourseService
import org.sopt.dateroad.data.dataremote.service.ProfileService
import org.sopt.dateroad.data.dataremote.service.UserPointService
import org.sopt.dateroad.data.repositoryimpl.UserInfoRepositoryImpl
import org.sopt.dateroad.di.qualifier.DateRoad
import org.sopt.dateroad.domain.repository.UserInfoRepository
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
    fun providesDummyService(@DateRoad retrofit: Retrofit): DummyService =
        retrofit.create(DummyService::class.java)

    @Provides
    fun providesMyCourseService(@DateRoad retrofit: Retrofit): MyCourseService =
        retrofit.create(MyCourseService::class.java)

    @Provides
    fun providesUserPointService(@DateRoad retrofit: Retrofit): UserPointService =
        retrofit.create(UserPointService::class.java)

    @Provides
    fun providesProfileService(@DateRoad retrofit: Retrofit): ProfileService =
        retrofit.create(ProfileService::class.java)

    @Provides
    fun provideUserInfoRepository(userInfoLocalDataSource: UserInfoLocalDataSource): UserInfoRepository =
        UserInfoRepositoryImpl(userInfoLocalDataSource)
}
