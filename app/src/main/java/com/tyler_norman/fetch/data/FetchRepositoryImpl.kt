package com.tyler_norman.fetch.data

import com.tyler_norman.fetch.domain.model.FetchItem
import com.tyler_norman.fetch.domain.repository.FetchRepository
import com.tyler_norman.fetch.ui.viewmodels.FetchViewModel
import javax.inject.Inject

/** Implementation for fetching list items. Implements [FetchRepository]. Error handling
 *  implemented in [FetchViewModel]. */
class FetchRepositoryImpl @Inject constructor(
    private val apiService: FetchApiService
) : FetchRepository {

    /** Retrieves screen content items from [FetchApiService]. Error handling implemented in
     * [FetchViewModel]. Use [FetchRepository] when connecting UI/presentation to back-end
     * services. */
    override suspend fun getFetchItems(): List<FetchItem> {
        return try {
            apiService.getFetchItems()
        } catch(e: Exception) {
            emptyList()
        }
    }
}
