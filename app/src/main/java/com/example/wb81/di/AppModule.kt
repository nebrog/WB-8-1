package com.example.wb81.di

import android.content.Context
import com.example.wb81.App
import com.example.wb81.data.CompositeRepository
import com.example.wb81.data.model.HeroesItem
import com.example.wb81.ui.Repository
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import javax.inject.Singleton

@ExperimentalStdlibApi
@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun clientOkhttp(): OkHttpClient {
        return OkHttpClient()
    }

    @Provides
    @Singleton
    fun adapter(): JsonAdapter<Map<String, HeroesItem>> {
        val moshi = Moshi.Builder().build()
        return moshi.adapter<Map<String, HeroesItem>>()
    }

    @Provides
    @Singleton
    fun provideRepository(repository: CompositeRepository): Repository {
        return repository
    }
}