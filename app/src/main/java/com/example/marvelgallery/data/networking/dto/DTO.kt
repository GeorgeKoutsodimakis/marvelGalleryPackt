package com.example.marvelgallery.data.networking.dto

class DataContainer<T>{
    val results: T ?=null
}

class DataWrapper<T>{
    var data:DataContainer<T>?=null
}

class ImageDTO{
    lateinit var path:String
    lateinit var extension:String

    val comleteImgPath:String
    get() = "$path.$extension"
}

class CharacterDTO{
    lateinit var name :String
    lateinit var thumbnail : ImageDTO

    val imgUrl:String
    get() = thumbnail.comleteImgPath
}