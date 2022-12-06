package com.example.faurecia_aptoidetechnicalchallenge.util

import com.example.faurecia_aptoidetechnicalchallenge.model.App
import com.google.gson.Gson
import com.ramcosta.composedestinations.navargs.DestinationsNavTypeSerializer
import com.ramcosta.composedestinations.navargs.NavTypeSerializer

@NavTypeSerializer
class AppTypeSerializer: DestinationsNavTypeSerializer<App> {

    val gson = Gson()

    override fun fromRouteString(routeStr: String): App {
        return gson.fromJson(routeStr,App::class.java)
    }

    override fun toRouteString(value: App): String {
        return gson.toJson(value)
    }
}