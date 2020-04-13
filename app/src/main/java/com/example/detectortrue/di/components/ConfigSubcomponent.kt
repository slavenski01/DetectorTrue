package com.example.detectortrue.di.components

import com.example.detectortrue.di.modules.ConfigModule
import com.example.detectortrue.di.scopes.ConfigScope
import com.example.detectortrue.presentation.config.ConfigPresenter
import dagger.Subcomponent

@ConfigScope
@Subcomponent(modules = [ConfigModule::class])
interface ConfigSubcomponent {
    fun inject(configPresenter: ConfigPresenter)
}