package com.vuoncog.data.di

import com.vuoncog.data.repository.ConversionRepository
import com.vuoncog.data.service.ConversionService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun providesConversionRepository(conversionService: ConversionService) =
        ConversionRepository(conversionService)
}