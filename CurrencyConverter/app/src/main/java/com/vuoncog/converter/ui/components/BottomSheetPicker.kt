package com.vuoncog.converter.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.vuoncog.converter.R
import com.vuoncog.converter.ui.theme.SystemColor
import com.vuoncog.converter.ui.theme.SystemFont
import com.vuoncog.converter.util.clickableWithoutRippleEffect
import kotlinx.coroutines.launch

@ExperimentalMaterial3Api
@Composable
fun BottomSheetPicker(
    modifier: Modifier = Modifier,
    dialogTitle: String,
    backButtonText: String = stringResource(R.string.back),
    onDismissRequest: () -> Unit = {},
    state: SheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
    content: @Composable () -> Unit,
) {
    val coroutineScope = rememberCoroutineScope()

    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        dragHandle = null,
        sheetState = state,
        modifier = modifier,
    ) {
        Column(
            modifier = Modifier
                .navigationBarsPadding()
                .background(Color.White)
                .padding(vertical = 16.dp),
        ) {
            Title(
                title = dialogTitle,
                backButtonText = backButtonText,
                onBackClick = {
                    coroutineScope.launch {
                        state.hide()
                    }.invokeOnCompletion {
                        onDismissRequest()
                    }
                },
                modifier = Modifier.padding(horizontal = 24.dp)
            )
            content()
        }
    }
}

@Composable
private fun Title(
    title: String,
    backButtonText: String,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {},
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 12.dp) then modifier
    ) {
        Text(
            text = backButtonText,
            style = SystemFont.Title2.textStyle,
            color = SystemColor.Primary.normal,
            modifier = Modifier
                .weight(1f)
                .clickableWithoutRippleEffect(true) { onBackClick() }
        )
        Text(
            text = title,
            style = SystemFont.Title2.textStyle,
            color = SystemColor.Neutral.neutral1,
        )
        Box(modifier = Modifier.weight(1f))
    }
}