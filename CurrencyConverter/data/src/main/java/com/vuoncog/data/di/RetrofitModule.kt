package com.vuoncog.data.di

import com.vuoncog.data.BuildConfig
import com.vuoncog.data.service.ConversionService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    @Singleton
    fun providesRetrofit(): Retrofit {
        val httpClient: OkHttpClient by lazy {
            val httpClient = OkHttpClient.Builder()

            httpClient
                .readTimeout(20, TimeUnit.SECONDS)
                .connectTimeout(20, TimeUnit.SECONDS)

            if (BuildConfig.DEBUG) {
                val logging = HttpLoggingInterceptor()
                logging.level = HttpLoggingInterceptor.Level.BODY
                httpClient.addInterceptor(logging)
                    .readTimeout(360, TimeUnit.SECONDS)
                    .connectTimeout(360, TimeUnit.SECONDS)
            }

            httpClient.build()
        }

        val retrofit: Retrofit by lazy {
            Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)
                .build()
        }
        return retrofit
    }

    @Provides
    @Singleton
    fun providesConversionService(retrofit: Retrofit): ConversionService =
        retrofit.create(ConversionService::class.java)
}