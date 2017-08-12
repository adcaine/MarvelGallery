package com.sample.marvelgallery.presenter

import com.sample.marvelgallery.data.MarvelRepository
import com.sample.marvelgallery.data.applySchedulers
import com.sample.marvelgallery.data.plusAssign
import com.sample.marvelgallery.data.smartSubscribe
import com.sample.marvelgallery.view.main.MainView

class MainPresenter(val view: MainView, val repository: MarvelRepository): BasePresenter() {

    fun onViewCreated() {
        loadCharacters()
    }

    fun onRefresh() {
        loadCharacters()
    }

    private fun loadCharacters() {
        subscriptions += repository.getAllCharacters()
                .applySchedulers()
                .smartSubscribe(
                        onStart = { view.refresh = true },
                        onSuccess = view::show,
                        onError = view::showError,
                        onFinish = { view.refresh = false }
                )
    }
}