package org.sopt.teamdateroad.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import org.sopt.teamdateroad.data.repositoryimpl.AdvertisementRepositoryImpl
import org.sopt.teamdateroad.data.repositoryimpl.AuthRepositoryImpl
import org.sopt.teamdateroad.data.repositoryimpl.CourseRepositoryImpl
import org.sopt.teamdateroad.data.repositoryimpl.MyCourseRepositoryImpl
import org.sopt.teamdateroad.data.repositoryimpl.ProfileRepositoryImpl
import org.sopt.teamdateroad.data.repositoryimpl.TimelineRepositoryImpl
import org.sopt.teamdateroad.data.repositoryimpl.UserInfoRepositoryImpl
import org.sopt.teamdateroad.data.repositoryimpl.UserPointRepositoryImpl
import org.sopt.teamdateroad.domain.repository.AdvertisementRepository
import org.sopt.teamdateroad.domain.repository.AuthRepository
import org.sopt.teamdateroad.domain.repository.CourseRepository
import org.sopt.teamdateroad.domain.repository.MyCourseRepository
import org.sopt.teamdateroad.domain.repository.ProfileRepository
import org.sopt.teamdateroad.domain.repository.TimelineRepository
import org.sopt.teamdateroad.domain.repository.UserInfoRepository
import org.sopt.teamdateroad.domain.repository.UserPointRepository

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

    @Binds
    @Singleton
    abstract fun bindsUserInfoRepository(userInfoRepositoryImpl: UserInfoRepositoryImpl): UserInfoRepository

    @Binds
    @Singleton
    abstract fun bindsUserPointRepository(userPointRepositoryImpl: UserPointRepositoryImpl): UserPointRepository
}
