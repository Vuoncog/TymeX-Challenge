package com.vuoncog.data.repository

import com.vuoncog.data.models.ResponseHandler
import com.vuoncog.data.response.ExchangeRateResponse
import com.vuoncog.data.response.SymbolsResponse
import com.vuoncog.data.service.ConversionService
import javax.inject.Inject

class ConversionRepository @Inject constructor(
    private val conversionService: ConversionService
) {
    suspend fun getAllSymbols(): ResponseHandler<SymbolsResponse.Symbols> {
        return try {
            val res = conversionService.getAllSymbols()
            ResponseHandler.success(res.symbols!!)
        } catch (e: Exception) {
            ResponseHandler.error(e)
        }
    }

    suspend fun getExchangeRate(
        symbols: List<String>,
    ): ResponseHandler<ExchangeRateResponse.Rates>{
        return try {
            val symbolsReq = symbols.joinToString(",")
            val res = conversionService.getExchangeRate(symbols = symbolsReq)
            ResponseHandler.success(res.rates!!)
        } catch (e: Exception){
            ResponseHandler.error(e)
        }
    }
}