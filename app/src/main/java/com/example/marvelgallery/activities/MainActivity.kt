package com.example.marvelgallery.activities

import MainPresenter
import android.os.Bundle
import android.view.Window
import androidx.recyclerview.widget.GridLayoutManager
import com.example.marvelgallery.R
import com.example.marvelgallery.adapter.CharacterItemAdapter
import com.example.marvelgallery.adapter.MainListAdapter
import com.example.marvelgallery.data.models.ModelCharacter
import com.example.marvelgallery.extensions.bindToSwipeRefresh
import com.example.marvelgallery.extensions.toast
import com.example.marvelgallery.repository.MarvelRepository
import com.example.marvelgallery.views.MainView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivityWithPresenter(), MainView {

    override var refresh by bindToSwipeRefresh(R.id.swipeRefreshView)
    override val presenter by lazy { MainPresenter(this, MarvelRepository.get()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_main)
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        swipeRefreshView.setOnRefreshListener { presenter.onRefresh() }
        presenter.onViewCreated()

    }

    override fun show(items: List<ModelCharacter>) {
        val categoryItemAdapters = items.map(::CharacterItemAdapter)
        recyclerView.adapter = MainListAdapter(categoryItemAdapters)
    }

    override fun showError(error: Throwable) {
        toast("Error: ${error.message}")
        error.printStackTrace()
    }

}