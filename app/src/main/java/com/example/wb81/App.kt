package com.example.wb81

import android.app.Application
import com.example.wb81.data.CompositeRepository
import com.example.wb81.data.FileRepository
import com.example.wb81.data.NetworkRepository
import com.example.wb81.data.model.HeroesItem
import com.example.wb81.ui.Repository
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import okhttp3.OkHttpClient

@ExperimentalStdlibApi
class App : Application() {

    lateinit var repository: Repository

    override fun onCreate() {
        super.onCreate()
        val okHttpClient: OkHttpClient = OkHttpClient()
        val moshi = Moshi.Builder().build()
        val mapAdapter = moshi.adapter<Map<String, HeroesItem>>()
        val networkRepository = NetworkRepository(okHttpClient, mapAdapter)
        val fileRepository = FileRepository(this, mapAdapter)
        repository = CompositeRepository(networkRepository, fileRepository)
    }
}