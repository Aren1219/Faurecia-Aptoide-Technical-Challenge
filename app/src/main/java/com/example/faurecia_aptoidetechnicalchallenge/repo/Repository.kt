package com.example.faurecia_aptoidetechnicalchallenge.repo

import com.example.faurecia_aptoidetechnicalchallenge.model.AptoideApiApps
import com.example.faurecia_aptoidetechnicalchallenge.util.Resource
import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun getAppList(): Flow<Resource<AptoideApiApps>>
}