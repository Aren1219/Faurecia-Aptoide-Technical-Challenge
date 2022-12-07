package com.example.faurecia_aptoidetechnicalchallenge.ui

import androidx.compose.foundation.lazy.LazyListState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.faurecia_aptoidetechnicalchallenge.model.AptoideApiApps
import com.example.faurecia_aptoidetechnicalchallenge.repo.Repository
import com.example.faurecia_aptoidetechnicalchallenge.util.CoroutineDispatcherProvider
import com.example.faurecia_aptoidetechnicalchallenge.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repo: Repository,
    private val dispatcherProvider: CoroutineDispatcherProvider
) : ViewModel() {

    private val _appList: MutableStateFlow<Resource<AptoideApiApps>> =
        MutableStateFlow(Resource.Loading())
    val appList: StateFlow<Resource<AptoideApiApps>> = _appList
    val editorsChoiceListState = LazyListState()
    val localTopAppsListState = LazyListState()

    init {
        getAppList()
    }

    private fun getAppList() = viewModelScope.launch(dispatcherProvider.io) {
        repo.getAppList().collectLatest { _appList.emit(it) }
    }
}