package com.example.faurecia_aptoidetechnicalchallenge.model


import com.google.gson.annotations.SerializedName

data class Time(
    @SerializedName("human")
    val human: String,
    @SerializedName("seconds")
    val seconds: Double
)