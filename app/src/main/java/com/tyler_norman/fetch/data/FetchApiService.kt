package com.tyler_norman.fetch.data

import com.tyler_norman.fetch.domain.model.FetchItem
import com.tyler_norman.fetch.domain.repository.FetchRepository
import retrofit2.http.GET

/** Retrofit interface for raw processing of Fetch List data. Do not use to hook up presentation
 * layer to API services; use [FetchRepository] instead.*/
sealed interface FetchApiService {
    /** main fetch api endpoint; gets list data for items. */
    @GET("hiring.json")
    suspend fun getFetchItems(): List<FetchItem>
}
