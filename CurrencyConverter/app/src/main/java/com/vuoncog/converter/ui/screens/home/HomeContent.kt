package com.vuoncog.converter.ui.screens.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.vuoncog.converter.R
import com.vuoncog.converter.ui.components.SystemButton
import com.vuoncog.converter.ui.components.SystemTextField
import com.vuoncog.converter.ui.models.ConversionSymbol
import com.vuoncog.converter.ui.models.TextFieldError
import com.vuoncog.converter.ui.models.TextFieldType
import com.vuoncog.converter.ui.theme.SystemColor
import com.vuoncog.converter.ui.theme.SystemFont
import com.vuoncog.converter.util.clickableWithoutRippleEffect
import com.vuoncog.converter.util.iconSize

@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Composable
fun HomeContent(
    paddingValues: PaddingValues,
    allSymbols: List<ConversionSymbol>,
    onFromCurrentSymbol: ConversionSymbol?,
    onFromSymbolSelected: (ConversionSymbol?) -> Unit,
    onToCurrentSymbol: ConversionSymbol?,
    onToSymbolSelected: (ConversionSymbol?) -> Unit,
    switchTwoSymbols: () -> Unit,
    inputValue: String,
    nearestValue: String,
    onValueChanged: (String) -> Unit,
    conversionValue: Double?,
    rateValue: Double?,
    errorMessageMap: Map<TextFieldType, TextFieldError>,
    disableButton: Boolean,
    calculateConversion: () -> Unit,
) {
    Column(
        modifier = Modifier.padding(paddingValues),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.padding(
                horizontal = 20.dp,
                vertical = 16.dp
            ),
            horizontalArrangement = Arrangement.spacedBy(24.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            HomeConversionSymbol(
                modifier = Modifier.weight(1f),
                allSymbols = allSymbols,
                currentSymbol = onFromCurrentSymbol,
                otherSelectedSymbol = onToCurrentSymbol,
                onConfirmClick = onFromSymbolSelected,
                title = stringResource(R.string.from)
            )

            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.arrows_repeat),
                contentDescription = null,
                tint = SystemColor.Neutral.neutral1,
                modifier = Modifier
                    .iconSize()
                    .clickableWithoutRippleEffect(true) {
                        switchTwoSymbols()
                    }
            )

            HomeConversionSymbol(
                modifier = Modifier.weight(1f),
                allSymbols = allSymbols,
                currentSymbol = onToCurrentSymbol,
                otherSelectedSymbol = onFromCurrentSymbol,
                onConfirmClick = onToSymbolSelected,
                title = stringResource(R.string.to)
            )
        }
        SystemTextField(
            modifier = Modifier.padding(
                horizontal = 20.dp,
                vertical = 12.dp
            ),
            textFieldType = TextFieldType.AMOUNT,
            value = inputValue,
            onValueChange = onValueChanged,
            error = errorMessageMap[TextFieldType.AMOUNT] ?: TextFieldError.NONE
        )

        SystemButton(
            modifier = Modifier,
            textContent = stringResource(R.string.calculate_conversion),
            disable = disableButton,
            onClick = calculateConversion
        )

        Spacer(modifier = Modifier.height(40.dp))

        conversionValue?.let {
            HomeResult(
                firstValue = "$nearestValue ${onFromCurrentSymbol?.symbols}",
                conversionValue = "$it ${onToCurrentSymbol?.symbols}",
                firstRateValue = "${1} ${onFromCurrentSymbol?.symbols}",
                conversionRateValue = "$rateValue ${onToCurrentSymbol?.symbols}"
            )
        }
    }

}

@Composable
private fun HomeResult(
    firstValue: String,
    conversionValue: String,
    firstRateValue: String,
    conversionRateValue: String,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(SystemColor.Neutral.neutral4)
            .padding(vertical = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            Text(
                text = firstValue,
                style = SystemFont.Title1.textStyle,
                color = SystemColor.Neutral.neutral1,
            )
            Text(
                text = stringResource(R.string.equal),
                style = SystemFont.Title1.textStyle,
                color = SystemColor.Neutral.neutral1,
                modifier = Modifier.padding(horizontal = 12.dp)
            )
            Text(
                text = conversionValue,
                style = SystemFont.Title1.textStyle,
                color = SystemColor.Neutral.neutral1,
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = firstRateValue,
                style = SystemFont.Title1.textStyle,
                color = SystemColor.Neutral.neutral3,
            )
            Text(
                text = stringResource(R.string.equal),
                style = SystemFont.Title1.textStyle,
                color = SystemColor.Neutral.neutral3,
                modifier = Modifier.padding(horizontal = 12.dp)
            )
            Text(
                text = conversionRateValue,
                style = SystemFont.Title1.textStyle,
                color = SystemColor.Neutral.neutral3,
            )
        }
    }
}