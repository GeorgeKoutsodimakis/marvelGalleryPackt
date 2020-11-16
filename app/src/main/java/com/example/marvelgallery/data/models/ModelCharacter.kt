package com.example.marvelgallery.data.models

import com.example.marvelgallery.data.networking.dto.CharacterDTO

data class ModelCharacter(@JvmField val name: String, @JvmField val image: String){
    constructor(dto: CharacterDTO):this(dto.name,dto.imgUrl)
}