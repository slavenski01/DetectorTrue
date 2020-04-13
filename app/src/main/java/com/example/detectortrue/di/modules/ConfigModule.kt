package com.example.detectortrue.di.modules

import com.example.detectortrue.di.scopes.ConfigScope
import com.example.detectortrue.model.data.storage.DataBaseProvider
import com.example.detectortrue.model.interactor.ConfigInteractor
import dagger.Module
import dagger.Provides

@Module
class ConfigModule {

    @Provides
    @ConfigScope
    fun provideInteractor(dataBaseProvider: DataBaseProvider): ConfigInteractor =
        ConfigInteractor(dataBaseProvider)
}