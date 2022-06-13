package com.example.seventhweekapppt1.ui

import com.example.seventhweekapppt1.data.model.HeroesItem

interface Repository {

    suspend fun getHeroes():Map<String,HeroesItem>
}