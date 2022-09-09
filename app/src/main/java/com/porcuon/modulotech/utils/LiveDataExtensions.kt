package com.porcuon.modulotech.utils

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun <T> LiveData<Event<T>>.observeEvent(
    lifecycleOwner: LifecycleOwner,
    onReceive: (T) -> Unit
) {
    val observer = Observer<Event<T>> { event ->
        val content: T = event.getContentIfNotHandled() ?: return@Observer
        onReceive(content)
    }
    observe(lifecycleOwner, observer)
}