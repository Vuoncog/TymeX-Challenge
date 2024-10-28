package com.vuoncog.converter.ui.screens.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.vuoncog.converter.R
import com.vuoncog.converter.ui.components.CrossfadeContent
import com.vuoncog.converter.ui.theme.SystemColor
import com.vuoncog.converter.ui.theme.SystemFont

@ExperimentalMaterial3Api
@ExperimentalFoundationApi
@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val localFocusManager = LocalFocusManager.current
    val homeUiState = homeViewModel.homeUiState.collectAsState()
    CrossfadeContent(
        state = homeUiState.value.symbols,
        onRefreshClicked = homeViewModel::refresh,
        loadingScreen = {
            Box(modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center){
                CircularProgressIndicator()
            }
        },
        contentScreen = { data ->
            Scaffold(
                containerColor = Color.White,
                modifier = Modifier
                    .pointerInput(Unit) {
                        detectTapGestures(
                            onTap = {
                                localFocusManager.clearFocus()
                            }
                        )
                    },
                topBar = {
                    Text(
                        text = stringResource(R.string.currency_converter),
                        style = SystemFont.Heading1.textStyle,
                        color = SystemColor.Neutral.neutral1,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 24.dp)
                    )
                }
            ) {
                HomeContent(
                    paddingValues = it,
                    inputValue = homeUiState.value.amount,
                    nearestValue = homeUiState.value.nearestAmount,
                    onValueChanged = homeViewModel::onAmountChanged,
                    allSymbols = data,
                    onFromCurrentSymbol = homeUiState.value.fromSymbol,
                    onFromSymbolSelected = homeViewModel::onFromSymbolSelected,
                    onToCurrentSymbol = homeUiState.value.toSymbol,
                    onToSymbolSelected = homeViewModel::onToSymbolSelected,
                    switchTwoSymbols = homeViewModel::switchTwoSymbols,
                    errorMessageMap = homeUiState.value.errorMessagesMap,
                    disableButton = homeUiState.value.disableButton,
                    conversionValue = homeUiState.value.conversionAmount,
                    calculateConversion = homeViewModel::calculateConversion,
                    rateValue = homeUiState.value.rate
                )
            }
        }
    )
}