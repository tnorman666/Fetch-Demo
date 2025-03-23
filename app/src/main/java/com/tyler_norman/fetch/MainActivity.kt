package com.tyler_norman.fetch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import com.tyler_norman.fetch.ui.screens.FetchScreen
import com.tyler_norman.fetch.ui.theme.FetchTheme
import com.tyler_norman.fetch.ui.viewmodels.FetchViewModel
import dagger.hilt.android.AndroidEntryPoint

/** Single activity for Fetch App. Sets Compose screen content. */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val fetchViewModel: FetchViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            val fetchItems = fetchViewModel.fetchItems.collectAsState().value
            val networkError = fetchViewModel.networkError.collectAsState().value

            FetchTheme {
                FetchScreen(
                    fetchItems = fetchItems,
                    networkError = networkError,
                )
            }
        }
    }
}
