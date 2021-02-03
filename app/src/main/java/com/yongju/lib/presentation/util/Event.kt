package com.yongju.lib.presentation.util

/**
 * Used as a wrapper for data that is exposed via a LiveData that represents an event.
 * See https://medium.com/androiddevelopers/livedata-with-snackbar-navigation-and-other-events-the-singleliveevent-case-ac2622673150
 */
data class Event<T>(private val content: T) {

    var hasBeenHandled = false
        private set // Allow external read but not write

    fun call(action: (T) -> Unit) {
        if (hasBeenHandled) {
            return
        }
        hasBeenHandled = true
        action(content)
    }

    fun peek() = content
}
