package com.vuoncog.converter.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vuoncog.converter.repository.ConversionUiRepository
import com.vuoncog.converter.ui.models.ConversionSymbol
import com.vuoncog.converter.ui.models.RequestState
import com.vuoncog.converter.ui.models.TextFieldError
import com.vuoncog.converter.ui.models.TextFieldType
import com.vuoncog.converter.validator.AmountValidator
import com.vuoncog.data.models.ErrorTag
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.math.BigDecimal
import java.math.RoundingMode
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val conversionUiRepository: ConversionUiRepository,
    private val amountValidator: AmountValidator,
) : ViewModel() {
    private val initErrorMessagesMap = mapOf(
        TextFieldType.AMOUNT to TextFieldError.NONE,
    )

    private val _homeUiState =
        MutableStateFlow(HomeUiState(errorMessagesMap = initErrorMessagesMap))
    val homeUiState = _homeUiState.asStateFlow()

    init {
        getAllSymbols()
        validToCalculate()
    }

    private fun getAllSymbols() {
        viewModelScope.launch {
            conversionUiRepository.symbolDataToUiData(
                onSuccess = {
                    _homeUiState.value = _homeUiState.value.copy(
                        symbols = RequestState.Success(data = it)
                    )
                },
                onError = {
                    _homeUiState.value = _homeUiState.value.copy(
                        symbols = RequestState.Error(it ?: ErrorTag.UNKNOWN)
                    )
                }
            )
        }
    }

    private fun validToCalculate() {
        val fromSymbol = _homeUiState.value.fromSymbol
        val toSymbol = _homeUiState.value.toSymbol
        val errorMessagesMap = _homeUiState.value.errorMessagesMap
        val amount = _homeUiState.value.amount

        val valid = fromSymbol != null && toSymbol != null
                && !errorMessagesMap.any { it.value != TextFieldError.NONE } && amount.isNotBlank()
        _homeUiState.value = _homeUiState.value.copy(
            disableButton = !valid
        )
    }

    fun refresh() {
        _homeUiState.value = _homeUiState.value.copy(
            symbols = RequestState.Loading
        )
        getAllSymbols()
        validToCalculate()
    }

    fun calculateConversion() {
        viewModelScope.launch {
            val amount = _homeUiState.value.amount
            val fromSymbol = _homeUiState.value.fromSymbol
            val toSymbol = _homeUiState.value.toSymbol
            conversionUiRepository.getExchangeRate(
                symbols = listOf(fromSymbol!!.symbols, toSymbol!!.symbols),
                onSuccess = {
                    val fromSymbolRate = it[fromSymbol.symbols]
                    val toSymbolRate = it[toSymbol.symbols]

                    val conversionAmount = amount.toDouble().div(fromSymbolRate ?: 1.0)
                        .times(toSymbolRate ?: 1.0)
                    val rate = (1.0).div(fromSymbolRate ?: 1.0)
                        .times(toSymbolRate ?: 1.0)

                    _homeUiState.value = _homeUiState.value.copy(
                        conversionAmount = BigDecimal(conversionAmount).setScale(
                            5,
                            RoundingMode.HALF_UP
                        ).toDouble(),
                        nearestAmount = amount,
                        rate = BigDecimal(rate).setScale(
                            5,
                            RoundingMode.HALF_UP
                        ).toDouble()
                    )
                },
                onError = {
                    _homeUiState.value = _homeUiState.value.copy(
                        symbols = RequestState.Error(it ?: ErrorTag.UNKNOWN)
                    )
                }
            )
        }
    }

    private fun amountValidation(amount: String): TextFieldError {
        return if (amount.isNotEmpty()) {
            amountValidator.validator(amount)
        } else {
            TextFieldError.NONE
        }
    }

    fun onAmountChanged(value: String) {
        val errorMessagesMap = _homeUiState.value.errorMessagesMap.toMutableMap()
        errorMessagesMap[TextFieldType.AMOUNT] = amountValidation(value)
        _homeUiState.value = _homeUiState.value.copy(
            amount = value,
            errorMessagesMap = errorMessagesMap
        )
        validToCalculate()
    }

    fun onFromSymbolSelected(symbol: ConversionSymbol?) {
        _homeUiState.value = _homeUiState.value.copy(
            fromSymbol = symbol
        )
        validToCalculate()
    }

    fun onToSymbolSelected(symbol: ConversionSymbol?) {
        _homeUiState.value = _homeUiState.value.copy(
            toSymbol = symbol
        )
        validToCalculate()
    }

    fun switchTwoSymbols() {
        val fromSymbol = _homeUiState.value.fromSymbol
        val toSymbol = _homeUiState.value.toSymbol
        _homeUiState.value = _homeUiState.value.copy(
            fromSymbol = toSymbol,
            toSymbol = fromSymbol,
        )
    }
}

data class HomeUiState(
    val amount: String = "",
    val fromSymbol: ConversionSymbol? = null,
    val toSymbol: ConversionSymbol? = null,
    val nearestAmount: String = "",
    val conversionAmount: Double? = null,
    val symbols: RequestState<List<ConversionSymbol>> = RequestState.Loading,
    val errorMessagesMap: Map<TextFieldType, TextFieldError>,
    val disableButton: Boolean = false,
    val rate: Double? = null,
)