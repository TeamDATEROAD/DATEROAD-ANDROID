package org.sopt.dateroad.di

import android.content.ContentResolver
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ContentResolverModule {
    @Provides
    @Singleton
    fun providesContentResolver(
        @ApplicationContext context: Context
    ): ContentResolver = context.contentResolver
}
