package com.example.core_di_impl.dependensies

import com.example.core_di_impl.injector.AppInjector

interface AppDependencies :
    NetworkDependencies,
    AndroidDependencies {

    companion object {
        val instance: AppDependencies
            get() = AppInjector.appComponent
    }
}
