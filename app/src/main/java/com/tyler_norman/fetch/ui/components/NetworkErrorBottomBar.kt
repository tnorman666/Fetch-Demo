package com.tyler_norman.fetch.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tyler_norman.fetch.ui.theme.FetchTheme
import androidx.compose.material3.Scaffold

/** Proof of concept error handling UI component. Intended to replace a screen level composables
 *  bottom bar, via the Compose [Scaffold].
 *
 *  @param error The [Exception] that caused an error.
 *
 *  @param modifier Compose [Modifier] for adjustments to this composable.
 *  */
@Composable
fun NetworkErrorBottomBar(
    error: Exception,
    modifier: Modifier = Modifier,
) {
    HorizontalDivider()
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth(),
    ) {
        Text(
            text = "Error: ${error.message ?: "Unknown Error."}\nPlease try again.",
            textAlign = TextAlign.Center,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewNetworkErrorBottomBar() {
    val error = Exception("404 - Not Found")
    FetchTheme {
        NetworkErrorBottomBar(
            error = error,
            modifier = Modifier.padding(16.dp)
        )
    }
}