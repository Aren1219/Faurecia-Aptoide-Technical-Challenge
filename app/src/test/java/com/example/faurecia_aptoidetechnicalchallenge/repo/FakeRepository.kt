package com.example.faurecia_aptoidetechnicalchallenge.repo

import com.example.faurecia_aptoidetechnicalchallenge.model.*
import com.example.faurecia_aptoidetechnicalchallenge.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeRepository : Repository {
    var shouldEmitError: Boolean = false
    override suspend fun getAppList(): Flow<Resource<AptoideApiApps>> = flow {
        emit(Resource.Loading())
        if (!shouldEmitError) emit(Resource.Success(getDummyResponse()))
        else emit(Resource.Error("Error message"))
    }
}

fun getDummyResponse() = AptoideApiApps(
    Responses(
        ListApps(
            Datasets(All(Data(0, 0, getDummyAppList(), 0, 0, 0), InfoX("", Time("", 0.0)))),
            InfoX("", Time("", 0.0))
        )
    ), ""
)

fun getDummyAppList(): List<App> {
    val list = mutableListOf<App>()
    for (i in 1..5) {
        list.add(getDummyApp(i))
    }
    return list
}

fun getDummyApp(id: Int = 1) = App(
    id = id,
    name = "Radio Itatiaia Belo Horizonte",
    packageX = "",
    storeId = 0,
    added = "2022-12-06 21:38:52",
    modified = "2022-12-06 22:38:35",
    updated = "2022-12-06 22:38:33",
    rating = 0.0,
    icon = "https://pool.img.aptoide.com/catappult/12ab02e680466969a0984d2c7c41373b_icon.png",
    graphic = "https://pool.img.aptoide.com/catappult/d6c8ab1a0af535c49a7da67bb22bd936_fgraphic.png",
    uptype = "webservice",
    apkTags = listOf(),
    downloads = 0,
    pdownloads = 0,
    md5sum = "",
    size = 7750288,
    storeName = "catappult",
    vercode = 10,
    vername = "1.2.0"
)