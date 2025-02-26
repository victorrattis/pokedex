package com.study.pokedex.data.utils

import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

// reference: https://proandroiddev.com/exploring-lazysuspend-in-kotlin-a-thread-safe-lazy-initialization-with-coroutines-4cf8e18f55ac
class LazySuspend<T>(private val initializer: suspend () -> T) {
    @Volatile
    private var cachedValue: T? = null
    private val mutex = Mutex()

    suspend fun getValue(): T {
        if (cachedValue != null) return cachedValue!!

        return mutex.withLock {
            if (cachedValue == null) {
                cachedValue = initializer()
            }
            cachedValue!!
        }
    }
}