import com.example.marvelgallery.extensions.applySchedulers
import com.example.marvelgallery.extensions.plusAssign
import com.example.marvelgallery.extensions.subscribeBy
import com.example.marvelgallery.presenters.BasePresenter
import com.example.marvelgallery.repository.MarvelRepository
import com.example.marvelgallery.views.MainView

class MainPresenter(private val view: MainView, val repository: MarvelRepository) :
    BasePresenter() {

    fun onViewCreated() {
        loadCharachters()
    }

    fun onRefresh() {
        loadCharachters()
    }

    private fun loadCharachters() {
        subscriptions += repository.getAllCharacters()
            .applySchedulers()
            .doOnSubscribe { view.refresh = true }
            .doFinally { view.refresh = false }
            .subscribeBy(
                onSuccess = view::show,
                onError = view::showError
            )
    }

}