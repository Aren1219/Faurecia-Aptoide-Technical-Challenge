package com.example.faurecia_aptoidetechnicalchallenge.repo

import com.example.faurecia_aptoidetechnicalchallenge.model.AptoideApiApps
import com.example.faurecia_aptoidetechnicalchallenge.remote.Api
import com.example.faurecia_aptoidetechnicalchallenge.util.Resource
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val api: Api
) : Repository {
    override suspend fun getAppList() = flow<Resource<AptoideApiApps>> {
        emit(Resource.Loading())
        try {
            val response = api.getAppList()
            if (response.isSuccessful)
                response.body()?.let { emit(Resource.Success(it)) }
            else
                emit(Resource.Error(response.code().toString()))
        } catch (E: HttpException) {
            emit(Resource.Error("Could not load data"))
        } catch (E: IOException) {
            emit(Resource.Error("Check internet"))
        }
    }
}