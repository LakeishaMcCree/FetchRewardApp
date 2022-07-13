package com.example.fetchrewardsapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fetchrewardsapp.model.FetchListItem
import com.example.fetchrewardsapp.repository.FetchRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FetchViewModel(): ViewModel() {
    private val repository: FetchRepository = FetchRepository()

    private val _fetchListItem = MutableLiveData<List<FetchListItem>>()
    val fetchListItem: LiveData<List<FetchListItem>>
        get() = _fetchListItem

    fun fetchList(){
        viewModelScope.launch(Dispatchers.IO) {
            _fetchListItem.postValue(repository.fetchItems())
        }
    }
}