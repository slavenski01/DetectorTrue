package com.example.detectortrue.di.components

import com.example.detectortrue.di.modules.PlayerModule
import com.example.detectortrue.di.scopes.PlayerScope
import com.example.detectortrue.presentation.player.PlayerPresenter
import dagger.Subcomponent

@PlayerScope
@Subcomponent(modules = [PlayerModule::class])
interface PlayerSubcomponent {
    fun inject(playerPresenter: PlayerPresenter)
}