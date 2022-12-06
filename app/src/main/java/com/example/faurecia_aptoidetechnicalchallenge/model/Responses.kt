package com.example.faurecia_aptoidetechnicalchallenge.model


import com.google.gson.annotations.SerializedName

data class Responses(
    @SerializedName("listApps")
    val listApps: ListApps
)