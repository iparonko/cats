package com.example.utils

fun <T> lazyThreadLocal(initializer: () -> T): Lazy<T> =
    lazy(LazyThreadSafetyMode.NONE, initializer)