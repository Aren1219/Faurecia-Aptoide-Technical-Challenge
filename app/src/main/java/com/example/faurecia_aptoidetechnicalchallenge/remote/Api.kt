package com.example.faurecia_aptoidetechnicalchallenge.remote

import com.example.faurecia_aptoidetechnicalchallenge.model.AptoideApiApps
import com.example.faurecia_aptoidetechnicalchallenge.util.Util.END_POINT
import retrofit2.Response
import retrofit2.http.GET

interface Api {

    @GET(END_POINT)
    suspend fun getAppList(): Response<AptoideApiApps>
}
