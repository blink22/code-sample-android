package com.blink22.sample.topKotlins.ui.utils

import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch


fun <E> Fragment.observe(events: Flow<E>, actions: (it: E) -> Unit) {
    lifecycleScope.launch {
        viewLifecycleOwner.repeatOnLifecycle(androidx.lifecycle.Lifecycle.State.STARTED) {
            events.collect {
                actions.invoke(it)
            }
        }
    }
}