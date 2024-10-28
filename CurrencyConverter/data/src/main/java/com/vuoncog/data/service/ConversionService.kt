package com.vuoncog.data.service

import com.vuoncog.data.BuildConfig
import com.vuoncog.data.response.ExchangeRateResponse
import com.vuoncog.data.response.SymbolsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ConversionService {
    @GET("symbols")
    suspend fun getAllSymbols(@Query("access_key") accessKey: String = BuildConfig.API_KEY): SymbolsResponse

    @GET("latest")
    suspend fun getExchangeRate(
        @Query("access_key") accessKey: String = BuildConfig.API_KEY,
        @Query("symbols") symbols: String,
    ): ExchangeRateResponse
}