package com.tyler_norman.fetch.domain.usecases

import com.tyler_norman.fetch.domain.model.FetchItem
import com.tyler_norman.fetch.domain.repository.FetchRepository
import javax.inject.Inject

/** Use case for performing transformative/filtering business logic on raw data retrieved form the
 *  [FetchRepository].
 *
 *  @property fetchRepository See [FetchRepository].
 *  */
class FetchUseCase @Inject constructor(
    private val fetchRepository: FetchRepository,
) {
    /** Takes raw backend data and groups/filters it into a list grouped by listId, then by the
     *  name of each listId's sub-items; also filters out items with null names. See [FetchItem]
     *  for more on structure of listIds and item names */
    suspend fun getGroupedFetchItems(): Map<Int, List<FetchItem>> {
        return fetchRepository.getFetchItems()
            .filter { item ->
                !item.name.isNullOrBlank()
            }
            .sortedWith(
                compareBy(
                    { item ->
                        item.listId
                    },
                    { item ->
                        item.name?.removePrefix("Item ")?.toIntOrNull() ?: Int.MAX_VALUE
                    }
                )
            )
            .groupBy { item ->
                item.listId
            }
    }
}
