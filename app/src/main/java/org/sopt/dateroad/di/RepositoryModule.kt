package org.sopt.dateroad.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import org.sopt.dateroad.data.repositoryimpl.AdvertisementRepositoryImpl
import org.sopt.dateroad.data.repositoryimpl.CourseRepositoryImpl
import org.sopt.dateroad.data.repositoryimpl.DummyRepositoryImpl
import org.sopt.dateroad.data.repositoryimpl.MyCourseRepositoryImpl
import org.sopt.dateroad.data.repositoryimpl.UserPointRepositoryImpl
import org.sopt.dateroad.domain.repository.AdvertisementRepository
import org.sopt.dateroad.domain.repository.CourseRepository
import org.sopt.dateroad.domain.repository.DummyRepository
import org.sopt.dateroad.domain.repository.MyCourseRepository
import org.sopt.dateroad.domain.repository.UserPointRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindsAdvertisementRepository(advertisementRepositoryImpl: AdvertisementRepositoryImpl): AdvertisementRepository

    @Binds
    @Singleton
    abstract fun bindsCourseRepository(courseRepositoryImpl: CourseRepositoryImpl): CourseRepository

    @Binds
    @Singleton
    abstract fun bindsDummyRepository(dummyRepositoryImpl: DummyRepositoryImpl): DummyRepository

    @Binds
    @Singleton
    abstract fun bindsMyCourseRepository(myCourseRepositoryImpl: MyCourseRepositoryImpl): MyCourseRepository

    @Binds
    @Singleton
    abstract fun bindsUserPointRepository(userPointRepositoryImpl: UserPointRepositoryImpl): UserPointRepository
}
