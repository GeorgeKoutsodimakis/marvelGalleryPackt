package com.example.marvelgallery.repository

import com.example.marvelgallery.data.models.ModelCharacter
import com.example.marvelgallery.data.networking.api.MarvelApi
import com.example.marvelgallery.data.networking.retrofit
import io.reactivex.rxjava3.core.Single

class MarvelRepositoryImpl : MarvelRepository {

    val api = retrofit.create(MarvelApi::class.java)

    override fun getAllCharacters(): Single<List<ModelCharacter>> =
        api.getCharacters(

        ).map {
            it.data?.results.orEmpty().map(::ModelCharacter)
        }

    companion object {
        const val elementsOnListLimit = 50
    }

}