package com.tyler_norman.fetch.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tyler_norman.fetch.domain.model.FetchItem
import com.tyler_norman.fetch.domain.usecases.FetchUseCase
import com.tyler_norman.fetch.ui.screens.FetchScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/** Viewmodel used in conjunction with the [FetchScreen] for UI state.
 *
 * @property fetchUseCase Use-case that performs business logic (filtering and grouping) on this
 * view-model's items.
 * */
@HiltViewModel
class FetchViewModel @Inject constructor(
    private val fetchUseCase: FetchUseCase,
) : ViewModel() {

    private val _fetchItems = MutableStateFlow<Map<Int, List<FetchItem>>>(emptyMap())
    val fetchItems = _fetchItems.asStateFlow()

    private val _networkError = MutableStateFlow<Exception?>(null)
    val networkError = _networkError.asStateFlow()

    init {
        loadAndGroupFetchItems()
    }

    /** Tied to asynchronous code execution in [FetchUseCase.getGroupedFetchItems]. Gets the
     *  [FetchScreen]'s data or an [Exception] upon some network error. */
    fun loadAndGroupFetchItems() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _fetchItems.value = fetchUseCase.getGroupedFetchItems()
            } catch (e: Exception) {
                _fetchItems.value = emptyMap()
                _networkError.value = e
            }
        }
    }
}
