package com.example.seventhweekapppt1

import android.app.Application
import com.example.seventhweekapppt1.data.CompositeRepository
import com.example.seventhweekapppt1.data.FileRepository
import com.example.seventhweekapppt1.data.NetworkRepository
import com.example.seventhweekapppt1.data.model.HeroesItem
import com.example.seventhweekapppt1.ui.Repository
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