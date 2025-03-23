package com.tyler_norman.fetch.domain.model

import com.google.gson.annotations.SerializedName
import com.tyler_norman.fetch.domain.repository.FetchRepository

/** Data model for items retrieved via [FetchRepository].
 * ex:
 * ```
 * _ Header - (ID 0, Fast Food Gift Cards)
 * | __ FetchItem(ID 0, ListID 0, , McDonalds)
 * | __ FetchItem(ID 1, ListID 0, , Burger King)
 * | __ FetchItem(ID 2, ListID 0, , Wendys)
 * ```
 *
 * @property id ID associated with specific item.
 *
 * @property listId Header level ID. e.g. 'Fast Food Giftcards' is a header while 'McDonald's' is a
 * specific item name; both with their own unique IDs.
 *
 * @property name THe name of the specific item
 * */
data class FetchItem(
    @SerializedName("id") val id: Int,
    @SerializedName("listId") val listId: Int,
    @SerializedName("name") val name: String?,
) {
    companion object {
        /** used to create mock data for UI previews and testing.
         *
         * @param headerCount The number of headers this factory should return; think of headers as
         * macro-level gift card categories.
         *
         * @param subItemCount The number of items under a specific header.
         * */
        fun factory(headerCount: Int, subItemCount: Int): Map<Int, List<FetchItem>> {
            val items = mutableListOf<FetchItem>()
            var currentId = 0

            for (listId in 0 until headerCount) {
                val subItems = mutableListOf<FetchItem>()
                for (subItemIndex in 0 until subItemCount) {
                    subItems.add(
                        FetchItem(
                            id = currentId + subItemIndex,
                            listId = listId,
                            name = "Item ${currentId + 1}"
                        )
                    )
                    currentId++
                }
                items.addAll(subItems)
            }

            return items.groupBy { it.listId }
        }
    }
}
