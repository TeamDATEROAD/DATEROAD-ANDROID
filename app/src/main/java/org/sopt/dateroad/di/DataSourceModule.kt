package org.sopt.dateroad.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import org.sopt.dateroad.data.datalocal.datasource.UserInfoLocalDataSource
import org.sopt.dateroad.data.datalocal.datasourceimpl.UserInfoLocalDataSourceImpl
import org.sopt.dateroad.data.dataremote.datasource.AdvertisementRemoteDataSource
import org.sopt.dateroad.data.dataremote.datasource.AuthRemoteDataSource
import org.sopt.dateroad.data.dataremote.datasource.CourseRemoteDataSource
import org.sopt.dateroad.data.dataremote.datasource.MyCourseRemoteDataSource
import org.sopt.dateroad.data.dataremote.datasource.ProfileRemoteDataSource
import org.sopt.dateroad.data.dataremote.datasource.TimelineRemoteDataSource
import org.sopt.dateroad.data.dataremote.datasource.UserPointRemoteDataSource
import org.sopt.dateroad.data.dataremote.datasourceimpl.AdvertisementRemoteDataSourceImpl
import org.sopt.dateroad.data.dataremote.datasourceimpl.AuthRemoteDataSourceImpl
import org.sopt.dateroad.data.dataremote.datasourceimpl.CourseRemoteDataSourceImpl
import org.sopt.dateroad.data.dataremote.datasourceimpl.MyCourseRemoteDataSourceImpl
import org.sopt.dateroad.data.dataremote.datasourceimpl.ProfileRemoteDataSourceImpl
import org.sopt.dateroad.data.dataremote.datasourceimpl.TimelineRemoteDataSourceImpl
import org.sopt.dateroad.data.dataremote.datasourceimpl.UserPointRemoteDataSourceImpl
import org.sopt.dateroad.data.repositoryimpl.UserInfoRepositoryImpl
import org.sopt.dateroad.domain.repository.UserInfoRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
    @Binds
    @Singleton
    abstract fun bindsUserInfoLocalDataSource(userInfoLocalDataSourceImpl: UserInfoLocalDataSourceImpl): UserInfoLocalDataSource

    @Binds
    @Singleton
    abstract fun bindsAdvertisementDataSource(advertisementRemoteDataSourceImpl: AdvertisementRemoteDataSourceImpl): AdvertisementRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindsAuthRemoteDataSource(authRemoteDataSourceImpl: AuthRemoteDataSourceImpl): AuthRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindsCourseRemoteDataSource(courseRemoteDataSourceImpl: CourseRemoteDataSourceImpl): CourseRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindsMyCourseRemoteDataSource(myCourseRemoteDataSourceImpl: MyCourseRemoteDataSourceImpl): MyCourseRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindsProfileDataSource(profileDataSourceImpl: ProfileRemoteDataSourceImpl): ProfileRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindTimelineRemoteDataSource(timelineRemoteDataSourceImpl: TimelineRemoteDataSourceImpl): TimelineRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindsUserPointRemoteDataSource(userPointRemoteDataSourceImpl: UserPointRemoteDataSourceImpl): UserPointRemoteDataSource
}
