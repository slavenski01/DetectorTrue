package com.example.detectortrue.di.modules

import com.example.detectortrue.di.scopes.PlayerScope
import com.example.detectortrue.model.data.storage.DataBaseProvider
import com.example.detectortrue.model.interactor.PlayerInteractor
import dagger.Module
import dagger.Provides

@Module
class PlayerModule {

    @Provides
    @PlayerScope
    fun provideInteractor(dataBaseProvider: DataBaseProvider): PlayerInteractor =
        PlayerInteractor(dataBaseProvider)
}