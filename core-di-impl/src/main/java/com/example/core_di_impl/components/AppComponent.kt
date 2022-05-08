package com.example.core_di_impl.components

import com.example.core_di.scopes.PerApplication
import com.example.core_di_impl.dependensies.AppDependencies
import com.example.core_di_impl.injector.BaseDependencies
import com.example.core_di_impl.modules.AppModule
import com.example.core_di_impl.modules.NetworkModule
import dagger.Component

@PerApplication
@Component(
    modules = [
        AppModule::class,
        NetworkModule::class
    ], dependencies = [BaseDependencies::class]
)
interface AppComponent : AppDependencies {

    @Component.Factory
    interface Factory {
        fun create(
            baseDependencies: BaseDependencies
        ): AppComponent
    }
}
