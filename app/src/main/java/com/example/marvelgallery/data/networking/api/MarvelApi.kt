package com.example.marvelgallery.data.networking.api

import com.example.marvelgallery.data.networking.dto.CharacterDTO
import com.example.marvelgallery.data.networking.dto.DataWrapper
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelApi {

    @GET("characters")
    fun getCharacters(): Single<DataWrapper<List<CharacterDTO>>>
}