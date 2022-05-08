package com.example.core_di_impl.injector

import android.app.Application
import com.example.core_di_impl.components.AppComponent
import com.example.core_di_impl.components.DaggerAppComponent

object AppInjector {
    private lateinit var baseDependencies: BaseDependencies

    private val appComponentLock = Object()

    @Volatile
    private var _appComponent: AppComponent? = null

    val appComponent: AppComponent
        get() {
            var localAppComponent = _appComponent
            if (localAppComponent != null) {
                return localAppComponent
            }
            synchronized(appComponentLock) {
                if (_appComponent == null) {
                    _appComponent = DaggerAppComponent.factory().create(
                        baseDependencies = baseDependencies
                    )
                    localAppComponent = _appComponent
                }
            }
            return localAppComponent!!
        }

    fun init(
        application: Application
    ) {
        baseDependencies = DaggerBaseComponent.factory()
            .create(
                application = application
            )
    }

    fun dropComponents() {
        synchronized(appComponentLock) {
            _appComponent = null
        }
    }
}
