package com.example.marvelgallery.activities

import androidx.appcompat.app.AppCompatActivity
import com.example.marvelgallery.presenters.Presenter

abstract class BaseActivityWithPresenter : AppCompatActivity() {
    abstract val presenter: Presenter

    override fun onDestroy() {
        super.onDestroy()
        presenter.onViewDestroyed()
    }
}