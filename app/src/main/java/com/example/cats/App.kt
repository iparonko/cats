package com.example.cats

import android.app.Application
import com.example.core_di_impl.injector.AppInjector

open class App : Application() {
    override fun onCreate() {
        super.onCreate()

        AppInjector.init(
            application = this
        )
    }
}