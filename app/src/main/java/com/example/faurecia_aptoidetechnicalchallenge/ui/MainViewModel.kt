package com.example.faurecia_aptoidetechnicalchallenge.ui

import androidx.compose.foundation.lazy.LazyListState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.faurecia_aptoidetechnicalchallenge.model.AptoideApiApps
import com.example.faurecia_aptoidetechnicalchallenge.repo.Repository
import com.example.faurecia_aptoidetechnicalchallenge.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repo: Repository
) : ViewModel() {

    private val _appList: MutableStateFlow<Resource<AptoideApiApps>> =
        MutableStateFlow(Resource.Loading())
    val appList: StateFlow<Resource<AptoideApiApps>> = _appList
    val editorsChoiceListState = LazyListState()
    val localTopAppsListState = LazyListState()

    init {
        getAppList()
    }

    private fun getAppList() = viewModelScope.launch {
        repo.getAppList().collectLatest { _appList.emit(it) }
    }

    fun getAppById(id: Int) =
        _appList.value.data?.responses?.listApps?.datasets?.all?.data?.list?.first {
            it.id == id
        }
}