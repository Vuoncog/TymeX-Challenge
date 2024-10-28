package com.vuoncog.converter.ui.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import com.vuoncog.converter.ui.models.RequestState

@ExperimentalMaterial3Api
@Composable
fun <T> CrossfadeContent(
    state: RequestState<T>,
    onRefreshClicked: () -> Unit,
    loadingScreen: @Composable () -> Unit,
    contentScreen: @Composable (T) -> Unit,
    idleScreen: @Composable () -> Unit = {},
) {
    AnimatedContent(
        targetState = state,
        transitionSpec = {
            fadeIn(
                animationSpec = tween(300)
            ) togetherWith fadeOut(animationSpec = tween(200))
        },
        label = "Transform animation"
    ) { targetState ->
        when (targetState) {
            is RequestState.Success -> contentScreen(targetState.data)
            is RequestState.Loading -> loadingScreen()
            is RequestState.Error -> ErrorScreen(targetState.error, onRefreshClicked)
            else -> idleScreen()
        }
    }
}