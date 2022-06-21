package com.example.wb81.ui

import com.example.wb81.data.model.HeroesItem

interface Repository {

    suspend fun getHeroes():Map<String,HeroesItem>
}