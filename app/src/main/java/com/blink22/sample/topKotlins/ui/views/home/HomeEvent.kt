package com.blink22.sample.topKotlins.ui.views.home


sealed class HomeEvent {

    /**
     * Should be called when Github repositories are loaded successfully.
     * */
    object OnRepositoriesLoaded : HomeEvent()

    /**
     * Should be called when Github repositories has failed to load.
     * */
    object OnError : HomeEvent()
}