package com.example.faurecia_aptoidetechnicalchallenge.model


import com.google.gson.annotations.SerializedName

data class InfoX(
    @SerializedName("status")
    val status: String,
    @SerializedName("time")
    val time: Time
)