package com.example.core_di_impl.modules

import android.app.Application
import android.content.Context
import com.example.core_di.DropDiComponents
import com.example.core_di.scopes.PerApplication
import com.example.core_di_impl.injector.AppInjector
import com.example.core_os.NetworkConnectionManager
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @PerApplication
    @Provides
    fun provideNetworkConnectionManager(context: Context): NetworkConnectionManager =
        NetworkConnectionManager(context = context)

    @Provides
    fun provideContext(
        application: Application
    ): Context {
        return application
    }

    @PerApplication
    @Provides
    fun provideDropDiComponents(): DropDiComponents {
        return object : DropDiComponents {
            override fun execute() {
                AppInjector.dropComponents()
            }
        }
    }

}
