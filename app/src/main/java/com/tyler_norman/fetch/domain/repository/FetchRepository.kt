package com.tyler_norman.fetch.domain.repository

import com.tyler_norman.fetch.domain.model.FetchItem
import com.tyler_norman.fetch.ui.screens.FetchScreen

/** Interface used to connect UI/presentation layer to back end services. Call this repository from
 *  a usecase to perform business logic on the raw data returned from the back end. */
interface FetchRepository {
    /** Retrieves a list of items associated with the [FetchScreen]. */
    suspend fun getFetchItems(): List<FetchItem>
}