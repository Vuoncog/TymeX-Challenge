package com.vuoncog.converter.ui.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

sealed class SystemFont(val textStyle: TextStyle) {
    object Title1 : SystemFont(
        textStyle = TextStyle(
            fontSize = 20.sp,
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Normal,
            letterSpacing = 0.4.sp,
            lineHeight = 24.sp,
        )
    )

    object Title2 : SystemFont(
        textStyle = TextStyle(
            fontSize = 16.sp,
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Medium,
            letterSpacing = 0.64.sp,
            lineHeight = 19.sp,
        )
    )

    object Body : SystemFont(
        textStyle = TextStyle(
            fontSize = 14.sp,
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Normal,
            letterSpacing = 0.14.sp,
            lineHeight = 16.sp,
        )
    )

    object Subtitle1 : SystemFont(
        textStyle = TextStyle(
            fontSize = 16.sp,
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Normal,
            letterSpacing = 0.32.sp,
            lineHeight = 19.sp,
        )
    )

    object Subtitle2 : SystemFont(
        textStyle = TextStyle(
            fontSize = 14.sp,
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Medium,
            letterSpacing = 0.28.sp,
            lineHeight = 16.sp
        )
    )

    object Heading2 : SystemFont(
        textStyle = TextStyle(
            fontSize = 28.sp,
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Medium,
            letterSpacing = 0.08.sp,
            lineHeight = 32.sp,
        )
    )

    object Heading1 : SystemFont(
        textStyle = TextStyle(
            fontSize = 24.sp,
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Medium,
            lineHeight = 28.sp,
            letterSpacing = 0.16.sp,
        )
    )

    object Caption : SystemFont(
        textStyle = TextStyle(
            fontSize = 12.sp,
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Normal,
            lineHeight = 14.sp,
            letterSpacing = 0.24.sp,
        )
    )
}