package com.example.seventhweekapppt1.data

import com.example.seventhweekapppt1.data.model.HeroesItem
import com.example.seventhweekapppt1.ui.Repository

@ExperimentalStdlibApi
class CompositeRepository(
    private val networkRepository: NetworkRepository,
    private val fileRepository: FileRepository,
) : Repository {

    override suspend fun getHeroes(): Map<String, HeroesItem> {
        try {
            val heroes = fileRepository.getHeroes()
            return heroes
        } catch(e:IllegalStateException) {
            val heroes = networkRepository.getHeroes()
            fileRepository.saveHeroes(heroes)
            return heroes
        }
    }
}