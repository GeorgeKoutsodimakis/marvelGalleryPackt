package com.example.marvelgallery

import MainPresenter
import com.example.marvelgallery.data.models.ModelCharacter
import com.example.marvelgallery.helpers.BaseMainView
import com.example.marvelgallery.helpers.Example
import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import io.reactivex.rxjava3.schedulers.Schedulers
import junit.framework.Assert.*
import org.junit.Before
import org.junit.Test
import com.example.marvelgallery.helpers.BaseMarvelRepository as BaseMarvelRepository


@Suppress("IllegalIdentifier")
class MainPresenterTest {

    @Before
    fun setUp() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler {
            Schedulers.trampoline()
        }
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setComputationSchedulerHandler {
            Schedulers.trampoline()
        }
        RxJavaPlugins.setNewThreadSchedulerHandler {
            Schedulers.trampoline()
        }
    }

    @Test
    fun Afterviewwascreatedlistofcharactersisloadedanddisplayed() {
        assertOnAction { onViewCreated() }.thereIsSameListDisplayed()
    }

    @Test
    fun Newlistisshownafterviewwasrefreshed() {
        assertOnAction { onRefresh() }.thereIsSameListDisplayed()
    }

    @Test
    fun WhenAPIreturnserroritisdisplayedonview() {
        // Given 
        val someError = Error()
        var errorDisplayed: Throwable? = null
        val view = BaseMainView(
            onShow = { _ -> fail() },
            onShowError = { errorDisplayed = it }
        )
        val marvelRepository = BaseMarvelRepository{ Single.error(someError) }
        val mainPresenter = MainPresenter(view, marvelRepository)
        view.onShow = { _ ->
            assertTrue(view.refresh)
        }
        // When 
        mainPresenter.onViewCreated()
        // Then 
        assertEquals(someError, errorDisplayed)
    }

    private fun assertOnAction(action: MainPresenter.() -> Unit) = PresenterActionAssertion(action)

    private class PresenterActionAssertion
        (val actionOnPresenter: MainPresenter.() -> Unit) {

        fun thereIsSameListDisplayed() {
            // Given
            var displayedList: List<ModelCharacter>? = null

            val view = BaseMainView(
                onShow = { items -> displayedList = items },
                onShowError = { fail() }
            )
            val marvelRepository = BaseMarvelRepository(
                onGetCharacters =
                { Single.just(Example.exampleCharacterList) }
            )

            val mainPresenter = MainPresenter(view, marvelRepository)

            // When
            mainPresenter.actionOnPresenter()

            // Then
            assertEquals(Example.exampleCharacterList, displayedList)
        }
    }
}