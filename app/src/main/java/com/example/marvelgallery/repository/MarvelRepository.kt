package com.example.marvelgallery.repository

import com.example.marvelgallery.data.models.ModelCharacter
import com.example.marvelgallery.data.networking.api.MarvelApi
import com.example.marvelgallery.data.networking.retrofit
import io.reactivex.Single

class MarvelRepository {
companion object{
    val limit = 50
}
    private val api: MarvelApi = retrofit.create(MarvelApi::class.java)
    fun getAllCharacters(searchQuery:String?): Single<List<ModelCharacter>> =
        api.getCharacters(offset = 0,limit = limit ).map {it.data?.results.orEmpty().map(::ModelCharacter) }
}