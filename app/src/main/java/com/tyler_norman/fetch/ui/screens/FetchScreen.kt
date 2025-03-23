package com.tyler_norman.fetch.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tyler_norman.fetch.domain.model.FetchItem
import com.tyler_norman.fetch.ui.components.ListHeader
import com.tyler_norman.fetch.ui.components.ListItem
import com.tyler_norman.fetch.ui.components.NetworkErrorBottomBar
import com.tyler_norman.fetch.ui.theme.FetchTheme

/** Main screen for demo Fetch app.
 *
 * @param fetchItems Items to populate the screen.
 *
 * @param networkError Nullable error that replaces the bottom bar when not null.
 *
 * @param modifier Compose [Modifier] for adjustments to this composable.
 * */
@Composable
fun FetchScreen(
    fetchItems: Map<Int, List<FetchItem>>,
    networkError: Exception?,
    modifier: Modifier = Modifier,
) {
    val expandedStates = rememberSaveable {
        mutableStateOf(fetchItems.keys.associateWith { false })
    }

    Scaffold(
        bottomBar = {
            networkError?.let { error ->
                NetworkErrorBottomBar(
                    error = error,
                )
            }
        },
        modifier = modifier
            .padding(16.dp)
            .fillMaxSize(),
    ) { contentPadding ->
        LazyColumn(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(contentPadding)
                .fillMaxSize(),
        ) {
            fetchItems.forEach { (listId, items) ->
                item {
                    ListHeader(
                        listId = listId,
                        isExpanded = expandedStates.value[listId] == true,
                        onExpandCollapseClick = {
                            expandedStates.value = expandedStates.value.toMutableMap().apply {
                                this[listId] = this[listId] != true
                            }
                        }
                    )
                }
                if (expandedStates.value[listId] == true) {
                    items(items.size) { index ->
                        ListItem(
                            name = items[index].name.toString(),
                            isLastItem = index == items.lastIndex
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewFetchScreen() {
    val list = FetchItem.factory(3,3)

    FetchTheme {
        FetchScreen(
            fetchItems = list,
            networkError = null
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewFetchScreen_NetworkError() {
    val list: Map<Int, List<FetchItem>> = emptyMap()
    val error = Exception("404 - Not Found")

    FetchTheme {
        FetchScreen(
            fetchItems = list,
            networkError = error
        )
    }
}
