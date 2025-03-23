package eg.edu.iti.mvvmlab.search.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SearchViewMode :ViewModel(){

    private val _searchQuery = MutableSharedFlow<String>()
    val searchQuery: SharedFlow<String> = _searchQuery

    private val allNames = listOf("Ahmed", "mohamed", "islam", "ziad", "youssef", "Bassem", "hassan")

    val filteredNames: StateFlow<List<String>> = searchQuery
        .map { query ->
            if (query.isBlank()) allNames
            else allNames.filter { it.startsWith(query, ignoreCase = true) }
        }
        .stateIn(viewModelScope, SharingStarted.Eagerly, allNames)

    fun setSearchQuery(query: String) {
        viewModelScope.launch {
            _searchQuery.emit(query)
        }
    }
}