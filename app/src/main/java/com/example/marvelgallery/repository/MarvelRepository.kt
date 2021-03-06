package com.example.marvelgallery.repository

import com.example.marvelgallery.data.Provider
import com.example.marvelgallery.data.models.ModelCharacter
import com.example.marvelgallery.data.networking.api.MarvelApi
import com.example.marvelgallery.data.networking.retrofit
import io.reactivex.rxjava3.core.Single

interface MarvelRepository {
//companion object{
//    const val limit = 50
//}
//    private val api: MarvelApi = retrofit.create(MarvelApi::class.java)
//    open fun getAllCharacters(): Single<List<ModelCharacter>> =
//        api.getCharacters().map {it.data?.results.orEmpty().map(::ModelCharacter) }

    fun getAllCharacters(): Single<List<ModelCharacter>>

    companion object : Provider<MarvelRepository>() {
        override fun creator() = MarvelRepositoryImpl()
    }

}