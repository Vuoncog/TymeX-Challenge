package com.vuoncog.converter.util

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.vuoncog.converter.ui.theme.SystemColor
import com.vuoncog.converter.util.UIConstants.ICON_SIZE
import com.vuoncog.converter.util.UIConstants.TEXT_FIELD_BORDER_SHAPE

fun Modifier.iconSize() = this then Modifier
    .size(ICON_SIZE)

fun Modifier.textFieldBorder(error: Boolean): Modifier {
    val borderColor = if (error) SystemColor.Semantic.danger else SystemColor.Neutral.neutral3
    return this then Modifier.border(
        width = 1.dp,
        color = borderColor,
        shape = TEXT_FIELD_BORDER_SHAPE
    )
}

fun Modifier.clickableWithoutRippleEffect(
    enabled: Boolean,
    onClick: () -> Unit,
) = composed(
        factory = {
            this then Modifier.clickable(
                onClick = onClick,
                enabled = enabled,
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            )
        }
    )

fun Modifier.symbolBackground(isSelected: Boolean): Modifier {
    val background = if (isSelected) SystemColor.Primary.light else Color.White
    return this then Modifier
        .background(background)
}