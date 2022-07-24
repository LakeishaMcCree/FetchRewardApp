package com.example.fetchrewardsapp.repository

import com.example.fetchrewardsapp.model.FetchListItem
import com.example.fetchrewardsapp.network.FetchApi

class FetchRepository() {
   private val fetchApi: FetchApi = FetchApi()

    suspend fun fetchItems(): List<FetchListItem> {
        var list = fetchApi.fetchItems()
        list = list.filter { it.name.isNullOrBlank() }
        list = sortList(list)

        return list
    }


    private fun sortList(fetchListItem: List<FetchListItem>): List<FetchListItem>{
        return fetchListItem.sortedWith(compareBy<FetchListItem> { item ->
            item.listId}.thenBy { item -> removeInt(item.name) })
        }
    }

    private fun removeInt(name: String): Int{
        val num = name.replace("Item ", "")
        return num.toInt()
}