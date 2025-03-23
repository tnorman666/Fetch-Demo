package com.tyler_norman.fetch.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tyler_norman.fetch.domain.model.FetchItem
import com.tyler_norman.fetch.domain.repository.FetchRepository
import com.tyler_norman.fetch.domain.usecases.FetchUseCase
import com.tyler_norman.fetch.ui.screens.FetchScreen
import com.tyler_norman.fetch.ui.theme.FetchTheme

/** Macro-level gift card header for further sub-division of items retrieved from
 * [FetchRepository.getFetchItems].
 *
 * @param listId ID associated with header; filtered/grouped by
 * [FetchUseCase.getGroupedFetchItems].
 *
 * @param isExpanded Boolean that dictates if the header should be expanded or collapsed. Logic for
 * this is implemented in [FetchScreen].
 *
 * @param onExpandCollapseClick Click callback for when a header is clicked. Logic for this is
 * implemented in [FetchScreen].
 *
 * @param modifier Compose [Modifier] for adjustments to this composable.
 * */
@Composable
fun ListHeader(
    listId: Int,
    isExpanded: Boolean,
    onExpandCollapseClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .background(
                    color = Color.LightGray,
                    shape = RoundedCornerShape(
                        topStart = 4.dp,
                        topEnd = 4.dp
                    )
                )
                .clickable { onExpandCollapseClick() }
                .fillMaxWidth(),
        ) {
            Text(
                text = "List ID: $listId",
                modifier = Modifier.padding(start = 8.dp)
            )
            IconButton(onClick = onExpandCollapseClick) {
                Icon(
                    imageVector = if (isExpanded) Icons.Default.KeyboardArrowUp
                        else Icons.Default.KeyboardArrowDown,
                    contentDescription = if (isExpanded) "Collapse" else "Expand"
                )
            }
        }
        HorizontalDivider(color = Color.Black)
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewListHeader_Expanded() {
    val list = FetchItem.factory(1,1)
    val listId = list.keys.first()

    FetchTheme {
        ListHeader(
            listId = listId,
            isExpanded = true,
            onExpandCollapseClick = {},
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewListHeader_Collapsed() {
    val list = FetchItem.factory(1,1)
    val listId = list.keys.first()

    FetchTheme {
        ListHeader(
            listId = listId,
            isExpanded = false,
            onExpandCollapseClick = {},
            modifier = Modifier.padding(16.dp)
        )
    }
}
