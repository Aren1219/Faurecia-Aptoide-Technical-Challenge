package com.example.faurecia_aptoidetechnicalchallenge.model


import com.google.gson.annotations.SerializedName

data class AptoideApiApps(
    @SerializedName("responses")
    val responses: Responses,
    @SerializedName("status")
    val status: String
)