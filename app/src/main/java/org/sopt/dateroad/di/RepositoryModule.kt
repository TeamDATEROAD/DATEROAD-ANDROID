package org.sopt.dateroad.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import org.sopt.dateroad.data.datalocal.datasource.UserInfoLocalDataSource
import org.sopt.dateroad.data.repositoryimpl.AdvertisementRepositoryImpl
import org.sopt.dateroad.data.repositoryimpl.AuthRepositoryImpl
import org.sopt.dateroad.data.repositoryimpl.CourseRepositoryImpl
import org.sopt.dateroad.data.repositoryimpl.MyCourseRepositoryImpl
import org.sopt.dateroad.data.repositoryimpl.ProfileRepositoryImpl
import org.sopt.dateroad.data.repositoryimpl.TimelineRepositoryImpl
import org.sopt.dateroad.data.repositoryimpl.UserInfoRepositoryImpl
import org.sopt.dateroad.data.repositoryimpl.UserPointRepositoryImpl
import org.sopt.dateroad.domain.repository.AdvertisementRepository
import org.sopt.dateroad.domain.repository.AuthRepository
import org.sopt.dateroad.domain.repository.CourseRepository
import org.sopt.dateroad.domain.repository.MyCourseRepository
import org.sopt.dateroad.domain.repository.ProfileRepository
import org.sopt.dateroad.domain.repository.TimelineRepository
import org.sopt.dateroad.domain.repository.UserInfoRepository
import org.sopt.dateroad.domain.repository.UserPointRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindsAdvertisementRepository(advertisementRepositoryImpl: AdvertisementRepositoryImpl): AdvertisementRepository

    @Binds
    @Singleton
    abstract fun bindAuthRepository(authRepositoryImpl: AuthRepositoryImpl): AuthRepository

    @Binds
    @Singleton
    abstract fun bindsCourseRepository(courseRepositoryImpl: CourseRepositoryImpl): CourseRepository

    @Binds
    @Singleton
    abstract fun bindsMyCourseRepository(myCourseRepositoryImpl: MyCourseRepositoryImpl): MyCourseRepository

    @Binds
    @Singleton
    abstract fun bindsProfileRepository(profileRepositoryImpl: ProfileRepositoryImpl): ProfileRepository

    @Binds
    @Singleton
    abstract fun bindsTimelineRepository(timelineRepositoryImpl: TimelineRepositoryImpl): TimelineRepository

    @Provides
    fun provideUserInfoRepository(userInfoLocalDataSource: UserInfoLocalDataSource): UserInfoRepository =
        UserInfoRepositoryImpl(userInfoLocalDataSource)

    @Binds
    @Singleton
    abstract fun bindsUserPointRepository(userPointRepositoryImpl: UserPointRepositoryImpl): UserPointRepository
}
