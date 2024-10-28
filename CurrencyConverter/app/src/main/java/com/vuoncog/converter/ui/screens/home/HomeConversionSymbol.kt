package com.vuoncog.converter.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.vuoncog.converter.R
import com.vuoncog.converter.ui.components.BottomSheetPicker
import com.vuoncog.converter.ui.models.ConversionSymbol
import com.vuoncog.converter.ui.theme.SystemColor
import com.vuoncog.converter.ui.theme.SystemFont
import com.vuoncog.converter.util.UIConstants.ICON_SIZE
import com.vuoncog.converter.util.clickableWithoutRippleEffect
import com.vuoncog.converter.util.iconSize
import com.vuoncog.converter.util.symbolBackground

@ExperimentalMaterial3Api
@Composable
fun HomeConversionSymbol(
    modifier: Modifier = Modifier,
    allSymbols: List<ConversionSymbol>,
    currentSymbol: ConversionSymbol?,
    otherSelectedSymbol: ConversionSymbol?,
    onConfirmClick: (ConversionSymbol?) -> Unit,
    title: String,
) {
    val showBottomSheet = remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .then(modifier)
            .clickableWithoutRippleEffect(true) { showBottomSheet.value = true },
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            color = SystemColor.Neutral.neutral3,
            style = SystemFont.Caption.textStyle
        )

        Text(
            text = currentSymbol?.symbols ?: stringResource(id = R.string.select_symbol),
            color = SystemColor.Neutral.neutral1,
            style = SystemFont.Title1.textStyle
        )
    }

    if (showBottomSheet.value) {
        SymbolsBottomSheet(
            allSymbols = allSymbols,
            currentSymbols = currentSymbol,
            otherSelectedSymbol = otherSelectedSymbol,
            onDismissClick = { showBottomSheet.value = false },
            onConfirmClick = {
                onConfirmClick(it)
                showBottomSheet.value = false
            })
    }
}

@ExperimentalMaterial3Api
@Composable
private fun SymbolsBottomSheet(
    allSymbols: List<ConversionSymbol>,
    currentSymbols: ConversionSymbol?,
    otherSelectedSymbol: ConversionSymbol?,
    onDismissClick: () -> Unit,
    onConfirmClick: (ConversionSymbol?) -> Unit,
) {
    BottomSheetPicker(
        dialogTitle = stringResource(R.string.select_symbol),
        onDismissRequest = onDismissClick,
    ) {
        LazyColumn {
            items(allSymbols) {
                val disableSymbol = otherSelectedSymbol == it
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .symbolBackground(currentSymbols == it)
                        .clickableWithoutRippleEffect(!disableSymbol) {
                            onConfirmClick(it)
                            onDismissClick()
                        }
                        .padding(vertical = 12.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(Modifier.size(ICON_SIZE))
                    Spacer(Modifier.width(8.dp))
                    Text(
                        text = "${it.detail} (${it.symbols})",
                        color = if (disableSymbol) SystemColor.Neutral.neutral3
                        else SystemColor.Neutral.neutral1
                    )
                    Spacer(Modifier.width(8.dp))
                    if (currentSymbols == it) {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.check),
                            tint = SystemColor.Primary.normal,
                            contentDescription = null,
                            modifier = Modifier.iconSize()
                        )
                    } else {
                        Box(Modifier.size(ICON_SIZE))
                    }
                }
            }
        }
    }
}