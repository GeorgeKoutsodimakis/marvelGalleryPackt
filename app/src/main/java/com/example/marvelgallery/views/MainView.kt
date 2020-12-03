package com.example.marvelgallery.views

import com.example.marvelgallery.data.models.ModelCharacter

interface MainView {
    var refresh: Boolean
    fun show(items: List<ModelCharacter>)
    fun showError(error: Throwable)
}