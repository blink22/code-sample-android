package com.blink22.sample.topKotlins.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupObservers()
    }

    open fun setupObservers() {
        /*
            The class that will extend BaseActivity can override this function
            and there is no specific or common implementation to be placed here
         */
    }

    //Should be called in onCreate to observe an event
    protected fun <E> observeEvent(events: Flow<E>, actions: (it: E) -> Unit) {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                events.collect {
                    actions.invoke(it)
                }
            }
        }
    }

}