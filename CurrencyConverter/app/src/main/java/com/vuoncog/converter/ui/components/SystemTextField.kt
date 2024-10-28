package com.vuoncog.converter.ui.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.vuoncog.converter.ui.models.TextFieldError
import com.vuoncog.converter.ui.models.TextFieldType
import com.vuoncog.converter.ui.models.TextFieldType.Companion.getErrorMessage
import com.vuoncog.converter.ui.theme.SystemColor
import com.vuoncog.converter.ui.theme.SystemFont
import com.vuoncog.converter.util.iconSize
import com.vuoncog.converter.util.textFieldBorder
import kotlinx.coroutines.launch

@ExperimentalFoundationApi
@Composable
fun SystemTextField(
    modifier: Modifier = Modifier,
    editable: Boolean = true,
    textFieldType: TextFieldType,
    maxLines: Int = 1,
    value: String,
    error: TextFieldError = TextFieldError.NONE,
    onValueChange: (String) -> Unit,
) {
    val errorMessage = textFieldType.getErrorMessage(error)
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 200,
                    easing = EaseOut
                )
            )
            .then(modifier)
    ) {
        Text(
            text = stringResource(id = textFieldType.label),
            style = SystemFont.Subtitle2.textStyle,
            color = SystemColor.Primary.normal
        )
        SystemCustomTextField(
            maxLines = maxLines,
            textFieldType = textFieldType, value = value,
            onValueChange = onValueChange,
            error = errorMessage != null,
            editable = editable
        )

        if (errorMessage != null) {
            Text(
                text = stringResource(id = errorMessage),
                style = SystemFont.Caption.textStyle,
                color = SystemColor.Semantic.danger
            )
        }
    }
}

@ExperimentalFoundationApi
@Composable
private fun SystemCustomTextField(
    modifier: Modifier = Modifier,
    editable: Boolean = true,
    textFieldType: TextFieldType,
    maxLines: Int = 1,
    value: String,
    onValueChange: (String) -> Unit,
    error: Boolean = false,
) {
    val bringIntoViewRequester = remember { BringIntoViewRequester() }
    val scope = rememberCoroutineScope()
    val focusManager = LocalFocusManager.current
    val keyboardOptions = when (textFieldType) {
        TextFieldType.AMOUNT ->
            KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            )

        else -> KeyboardOptions(
            imeAction = ImeAction.Done
        )
    }

    BasicTextField(
        modifier = Modifier
            .bringIntoViewRequester(bringIntoViewRequester)
            .then(modifier),
        enabled = editable,
        value = value,
        maxLines = maxLines,
        onTextLayout = {
            scope.launch {
                if (it.lineCount >= 2) {
                    bringIntoViewRequester.bringIntoView()
                }
            }
        },
        keyboardActions = KeyboardActions(
            onDone = {
                focusManager.clearFocus(true)
            }
        ),
        onValueChange = onValueChange,
        keyboardOptions = keyboardOptions,
        textStyle = SystemFont.Subtitle1.textStyle,
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier
                    .textFieldBorder(error)
                    .padding(12.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                textFieldType.leadingIcon?.let {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = it),
                        contentDescription = null,
                        modifier = Modifier.iconSize(),
                        tint = SystemColor.Neutral.neutral3
                    )
                }
                Box(
                    contentAlignment = Alignment.TopStart,
                    modifier = Modifier.weight(1f)
                ) {
                    if (value.isBlank()) {
                        Text(
                            text = stringResource(id = textFieldType.placeholder),
                            style = SystemFont.Subtitle1.textStyle,
                            color = SystemColor.Neutral.neutral3,
                        )
                    }
                    innerTextField()
                }
            }
        }
    )
}