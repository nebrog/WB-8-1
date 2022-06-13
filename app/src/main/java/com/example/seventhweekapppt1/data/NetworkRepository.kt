package com.example.seventhweekapppt1.data

import com.example.seventhweekapppt1.data.model.HeroesItem
import com.example.seventhweekapppt1.ui.Repository
import com.squareup.moshi.JsonAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import ru.gildor.coroutines.okhttp.await

class NetworkRepository(
    private val okHttpClient: OkHttpClient,
    private val adapter: JsonAdapter<Map<String, HeroesItem>>
) : Repository {

    companion object {
        private const val URL = "https://api.opendota.com/api/constants/heroes"
    }

    override suspend fun getHeroes(): Map<String, HeroesItem> {
        return withContext(Dispatchers.IO) {
            val request: Request = Request.Builder().url(URL).build()
            val result = okHttpClient.newCall(request).await()
            if (!result.isSuccessful) throw IllegalStateException("Unexpected code ${result.code}")
            val heroMap = adapter.fromJson(result.body!!.source())
            if (heroMap != null) {
                heroMap
            } else {
                throw IllegalStateException("Не удалось распарсить")
            }
        }
    }
}