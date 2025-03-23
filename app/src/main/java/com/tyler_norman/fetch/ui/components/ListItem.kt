package com.tyler_norman.fetch.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tyler_norman.fetch.domain.model.FetchItem
import com.tyler_norman.fetch.ui.theme.FetchTheme
import com.tyler_norman.fetch.ui.screens.FetchScreen

/** Reusable component for individual items of the [FetchScreen].
 *
 * @param name The name of the item; given via the [FetchItem] data model.
 *
 * @param isLastItem Boolean used to conditionally add padding to the bottom of the last item and
 * change the horizontal dividers color. Logic for this is implemented in [FetchScreen].
 *
 * @param modifier Compose [Modifier] for adjustments to this composable.
 * */
@Composable
fun ListItem(
    name: String,
    isLastItem: Boolean,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start,
        modifier = modifier
            .fillMaxWidth()
    ) {
        Text(
            text = "Name: $name",
            modifier = Modifier.padding(start = 12.dp)
        )
        if (isLastItem) {
            HorizontalDivider(color = Color.Black)
            Spacer(Modifier.padding(bottom = 16.dp))
        } else {
            HorizontalDivider()
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewListItem_NotLast() {
    val items = FetchItem.factory(1, 1)
    val firstItem = items[0]?.firstOrNull()

    FetchTheme {
        firstItem?.let {
            ListItem(
                name = it.name.orEmpty(),
                isLastItem = false,
                modifier = Modifier.padding(16.dp),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewListItem_IsLast() {
    val items = FetchItem.factory(1, 1)
    val firstItem = items[0]?.firstOrNull()

    FetchTheme {
        firstItem?.let {
            ListItem(
                name = it.name.orEmpty(),
                isLastItem = true,
                modifier = Modifier.padding(16.dp),
            )
        }
    }
}
