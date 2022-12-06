package com.example.faurecia_aptoidetechnicalchallenge.util

import com.example.faurecia_aptoidetechnicalchallenge.model.App

object Util {

    const val BASE_URL = "http://ws2.aptoide.com"
    const val END_POINT = "/api/6/bulkRequest/api_list/listApps"

    fun previewApp(): App = App(
        "",
        listOf(),
        1,
        "https://pool.img.aptoide.com/james34/dfe13823b5ee8e3cbc2cf0493ccb2ea9_fgraphic.png",
        "https://pool.img.aptoide.com/james34/84d7ec01238747098c0f370062ab8745_icon.png",
        1,
        "",
        "",
        "VPN Free - Betternet Hotspot VPN & Private Browser",
        "",
        1,
        0.0,
        1,
        1,
        "",
        "",
        "",
        1,
        ""
    )

    fun previewAppList(count: Int = 5): List<App> {
        val list: MutableList<App> = mutableListOf()
        for (i in 1..5) {
            list.add(previewApp())
        }
        return list
    }
}