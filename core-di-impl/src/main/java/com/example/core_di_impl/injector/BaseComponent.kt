package com.example.core_di_impl.injector

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component()
@Singleton
interface BaseComponent : BaseDependencies {

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance application: Application
        ): BaseComponent
    }
}
