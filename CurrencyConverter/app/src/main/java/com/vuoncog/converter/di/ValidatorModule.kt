package com.vuoncog.converter.di

import com.vuoncog.converter.validator.AmountValidator
import com.vuoncog.converter.validator.ValidationPattern
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ValidatorModule {

    @Provides
    @Singleton
    fun providesAmountValidation(): ValidationPattern =
        AmountValidator()
}