package com.example.marvelgallery.helpers

import com.example.marvelgallery.data.models.ModelCharacter
import com.example.marvelgallery.repository.MarvelRepository
import com.example.marvelgallery.views.MainView
import io.reactivex.rxjava3.core.Single


open class BaseMarvelRepository(val onGetCharacters: () -> Single<List<ModelCharacter>>) : MarvelRepository() {

    override fun getAllCharacters(): Single<List<ModelCharacter>> = onGetCharacters()
}

class BaseMainView(
    var onShow: (items: List<ModelCharacter>) -> Unit = {},
    val onShowError: (error: Throwable) -> Unit = {},
    override var refresh: Boolean = false
) : MainView {

    override fun show(items: List<ModelCharacter>) {
        onShow(items)
    }

    override fun showError(error: Throwable) {
        onShowError(error)
    }
}

// test source set
object Example {
    val exampleCharacter = ModelCharacter("ExampleName", "ExampleImageUrl")
    val exampleCharacterList = listOf(
        exampleCharacter,
        ModelCharacter("Name1", "ImageUrl1"),
        ModelCharacter("Name2", "ImageUrl2")
    )
}