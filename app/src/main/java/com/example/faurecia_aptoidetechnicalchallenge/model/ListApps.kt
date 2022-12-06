package com.example.faurecia_aptoidetechnicalchallenge.model


import com.google.gson.annotations.SerializedName

data class ListApps(
    @SerializedName("datasets")
    val datasets: Datasets,
    @SerializedName("info")
    val info: InfoX
)