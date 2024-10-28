package com.vuoncog.converter.ui.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalMinimumInteractiveComponentEnforcement
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.vuoncog.converter.ui.theme.SystemColor
import com.vuoncog.converter.ui.theme.SystemFont

@ExperimentalMaterial3Api
@Composable
fun SystemButton(
    modifier: Modifier = Modifier,
    textContent: String,
    disable: Boolean = false,
    plusPadding: Int = 0,
    buttonColor: GogoXButtonColor = GogoXButtonColor.ButtonColor,
    onClick: () -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    val containerColor =
        if (isPressed) buttonColor.pressedContainerColor else buttonColor.normalContainerColor
    val paddingValues = PaddingValues(
        vertical = 12.dp,
        horizontal = (24.plus(plusPadding)).dp
    )

    CompositionLocalProvider(LocalMinimumInteractiveComponentEnforcement provides false) {
        Button(
            onClick = onClick,
            contentPadding = paddingValues,
            shape = CircleShape,
            modifier = Modifier
                .then(modifier),
            colors = ButtonDefaults.buttonColors(
                containerColor = containerColor,
                contentColor = buttonColor.normalContentColor,
                disabledContainerColor = buttonColor.disabledContainerColor,
                disabledContentColor = buttonColor.disabledContentColor
            ),
            enabled = !disable,
            interactionSource = interactionSource
        ) {
            Text(
                text = textContent,
                style = SystemFont.Title2.textStyle
            )
        }
    }
}

open class GogoXButtonColor(
    val normalContentColor: Color,
    val normalContainerColor: Color,
    val pressedContentColor: Color,
    val pressedContainerColor: Color,
    val disabledContentColor: Color,
    val disabledContainerColor: Color,
) {
    companion object {
        fun copy(
            normalContentColor: Color = Color.Transparent,
            normalContainerColor: Color = Color.Transparent,
            pressedContentColor: Color = Color.Transparent,
            pressedContainerColor: Color = Color.Transparent,
            disabledContentColor: Color = Color.Transparent,
            disabledContainerColor: Color = Color.Transparent,
        ) = GogoXButtonColor(
            normalContentColor, normalContainerColor, pressedContentColor,
            pressedContainerColor, disabledContentColor, disabledContainerColor
        )
    }

    object ButtonColor : GogoXButtonColor(
        normalContentColor = Color.White,
        normalContainerColor = SystemColor.Primary.normal,
        pressedContentColor = Color.White.copy(0.9f),
        pressedContainerColor = SystemColor.Primary.dark,
        disabledContentColor = Color.White.copy(0.6f),
        disabledContainerColor = SystemColor.Primary.light,
    )
}