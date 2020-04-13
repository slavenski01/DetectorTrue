package com.example.detectortrue.di.components

import android.content.Context
import com.example.detectortrue.di.modules.ConfigModule
import com.example.detectortrue.di.modules.NavigationModule
import com.example.detectortrue.di.modules.PlayerModule
import com.example.detectortrue.model.data.storage.DataBaseProvider
import com.example.detectortrue.ui.AppActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NavigationModule::class])
interface AppComponent {
    val context: Context

    val dataBaseProvider: DataBaseProvider

    fun inject(appActivity: AppActivity)

    fun addConfigSubcomponent(configModule: ConfigModule): ConfigSubcomponent
    fun addPlayerSubcomponent(playerModule: PlayerModule): PlayerSubcomponent

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder

        @BindsInstance
        fun dataBaseProvider(dataBaseProvider: DataBaseProvider): Builder

        fun build(): AppComponent
    }
}