package org.sopt.dateroad.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import org.sopt.dateroad.data.repositoryimpl.AuthRepositoryImpl
import org.sopt.dateroad.data.repositoryimpl.DummyRepositoryImpl
import org.sopt.dateroad.data.repositoryimpl.MyCourseRepositoryImpl
import org.sopt.dateroad.domain.repository.AuthRepository
import org.sopt.dateroad.domain.repository.DummyRepository
import org.sopt.dateroad.domain.repository.MyCourseRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindsDummyRepository(dummyRepositoryImpl: DummyRepositoryImpl): DummyRepository

    @Binds
    @Singleton
    abstract fun bindsMyCourseRepository(myCourseRepositoryImpl: MyCourseRepositoryImpl): MyCourseRepository

    @Binds
    @Singleton
    abstract fun bindsAuthRepository(authRepositoryImpl: AuthRepositoryImpl): AuthRepository
}
