package com.vuoncog.converter.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.vuoncog.converter.R
import com.vuoncog.converter.ui.theme.SystemColor
import com.vuoncog.converter.ui.theme.SystemFont
import com.vuoncog.data.models.ErrorTag
import com.vuoncog.data.models.ErrorTag.CONNECTIVITY
import com.vuoncog.data.models.ErrorTag.Companion.errorTagToMessage
import com.vuoncog.data.models.ErrorTag.SERVER
import com.vuoncog.data.models.ErrorTag.UNKNOWN

@ExperimentalMaterial3Api
@Composable
fun ErrorScreen(
    errorTag: ErrorTag,
    onRefreshClicked: () -> Unit
) {
    val context = LocalContext.current
    val image = when (errorTag) {
        CONNECTIVITY -> painterResource(id = R.drawable.no_wifi)
        SERVER -> painterResource(id = R.drawable.no_internet)
        UNKNOWN -> painterResource(id = R.drawable.unknown_search)
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 64.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = image, contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
            )
            Text(
                text = errorTag.errorTagToMessage(context),
                style = SystemFont.Heading1.textStyle,
                color = SystemColor.Neutral.neutral1
            )

            SystemButton(
                textContent = stringResource(R.string.refresh),
                onClick = onRefreshClicked
            )
        }
    }
}