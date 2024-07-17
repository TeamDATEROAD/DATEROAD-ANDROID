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
import org.sopt.dateroad.data.dataremote.datasource.DateRemoteDataSource
import org.sopt.dateroad.data.dataremote.datasource.DummyRemoteDataSource
import org.sopt.dateroad.data.dataremote.datasource.MyCourseRemoteDataSource
import org.sopt.dateroad.data.dataremote.datasource.ProfileRemoteDataSource
import org.sopt.dateroad.data.dataremote.datasource.UserPointRemoteDataSource
import org.sopt.dateroad.data.dataremote.datasourceimpl.AdvertisementRemoteDataSourceImpl
import org.sopt.dateroad.data.dataremote.datasourceimpl.AuthRemoteDataSourceImpl
import org.sopt.dateroad.data.dataremote.datasourceimpl.CourseRemoteDataSourceImpl
import org.sopt.dateroad.data.dataremote.datasourceimpl.DateRemoteDataSourceImpl
import org.sopt.dateroad.data.dataremote.datasourceimpl.DummyRemoteDataSourceImpl
import org.sopt.dateroad.data.dataremote.datasourceimpl.MyCourseRemoteDataSourceImpl
import org.sopt.dateroad.data.dataremote.datasourceimpl.ProfileRemoteDataSourceImpl
import org.sopt.dateroad.data.dataremote.datasourceimpl.UserPointRemoteDataSourceImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
    @Binds
    @Singleton
    abstract fun bindsUserInfoLocalDataSource(userInfoLocalDataSourceImpl: UserInfoLocalDataSourceImpl): UserInfoLocalDataSource

    @Binds
    @Singleton
    abstract fun bindAdvertisementRemoteDataSource(advertisementRemoteDataSourceImpl: AdvertisementRemoteDataSourceImpl): AdvertisementRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindsAuthRemoteDataSource(authRemoteDataSourceImpl: AuthRemoteDataSourceImpl): AuthRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindsCourseRemoteDataSource(courseRemoteDataSourceImpl: CourseRemoteDataSourceImpl): CourseRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindDateRemoteDataSource(dateRemoteDataSourceImpl: DateRemoteDataSourceImpl): DateRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindsDummyRemoteDataSource(dummyRemoteDataSourceImpl: DummyRemoteDataSourceImpl): DummyRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindsMyCourseRemoteDataSource(myCourseRemoteDataSourceImpl: MyCourseRemoteDataSourceImpl): MyCourseRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindsUserPointRemoteDataSource(userPointRemoteDataSourceImpl: UserPointRemoteDataSourceImpl): UserPointRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindsProfileDataSource(profileDataSourceImpl: ProfileRemoteDataSourceImpl): ProfileRemoteDataSource
}
